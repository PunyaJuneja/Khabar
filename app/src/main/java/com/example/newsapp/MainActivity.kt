package com.example.newsapp
import android.net.Uri
import android.app.DownloadManager
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.PixelCopy.request
import androidx.appcompat.widget.SearchView

import com.android.volley.toolbox.Volley
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.request.RequestListener
import com.example.newsapp.databinding.ActivityMainBinding
import com.google.androidgamesdk.gametextinput.Listener
import com.android.volley.Response
import com.example.memechat.Pageradapter
import com.google.android.material.tabs.TabLayout
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var newsarray:ArrayList<News>
    private lateinit var mAdapter : NewsListAdapter
    private lateinit var tempArayList:ArrayList<News>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
      //  supportActionBar!!.setHomeButtonEnabled(true)
        val viewpager = findViewById<ViewPager>(R.id.fragmentconainer)
        viewpager.adapter = Pageradapter(supportFragmentManager)
        val tablayout = findViewById<TabLayout>(R.id.include)
        tablayout.setupWithViewPager(viewpager)
       }
    }