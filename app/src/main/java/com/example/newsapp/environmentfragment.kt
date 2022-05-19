package com.example.newsapp

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import java.util.*
import kotlin.collections.ArrayList


class environmentfragment: Fragment() , Newsitemclicked{

    lateinit var newsarray    :ArrayList<News>
    lateinit var  madapter   : NewsListAdapter
    lateinit var tempArayList :ArrayList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.environment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclierviewofgeneral: RecyclerView =requireView().findViewById(R.id.recyclierviewofenvironment)
        recyclierviewofgeneral.layoutManager = LinearLayoutManager(requireActivity())
        fetchdata()
        madapter = NewsListAdapter(this)
        recyclierviewofgeneral.adapter=madapter
    }

    override fun onitemclicked(item: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireActivity(), Uri.parse(item.url))
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_main,menu)
        val item =menu?.findItem(R.id.search_action)
        val searchview = item?.actionView as SearchView
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                tempArayList.clear()
                val searchtext = newText!!.toLowerCase(Locale.getDefault())
                if(searchtext.isNotEmpty())
                {
                    newsarray.forEach{
                        if(it.title.toLowerCase(Locale.getDefault()).contains(searchtext))
                        {
                            tempArayList.add(it)
                        }
                    }
                    madapter.updateitems(tempArayList)

                }
                else
                {
                    tempArayList.clear()
                    tempArayList.addAll(newsarray)
                    madapter.updateitems(newsarray)
                }
                return false

            }
        })

        return super.onCreateOptionsMenu(menu,inflater)
    }
    private fun fetchdata() {
        val url = "https://saurav.tech/NewsAPI/top-headlines/category/science/in.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val newsjsonarray = it.getJSONArray("articles")
                newsarray = arrayListOf<News>()
                tempArayList= arrayListOf<News>()
                for (i in 0 until newsjsonarray.length()) {
                    val newsjsonobject = newsjsonarray.getJSONObject(i)
                    val news = News(
                        newsjsonobject.getString("title"),
                        newsjsonobject.getString("author"),
                        newsjsonobject.getString("url"),
                        newsjsonobject.getString("urlToImage")
                    )
                    newsarray.add(news)
                    tempArayList.addAll(newsarray)
                }
                madapter.updateitems(newsarray)
            },
            Response.ErrorListener {

            }
        )

        MySingleton.getInstance(requireActivity()).addToRequestQueue(jsonObjectRequest)


    }
}