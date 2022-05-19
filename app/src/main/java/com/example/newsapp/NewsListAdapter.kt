package com.example.newsapp

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: Newsitemclicked): RecyclerView.Adapter<NewsViewHolder>() {
   private val items:ArrayList<News> = ArrayList()
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
      val viewHolder = NewsViewHolder(view)
      view.setOnClickListener{
         listener.onitemclicked(items[viewHolder.adapterPosition])
      }
      return viewHolder
   }

   override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
   val currentitem = items[position]
      holder.titleview.text = currentitem.title
      holder.author.text = currentitem.author
      Glide.with(holder.itemView.context).load(currentitem.imageurl).into(holder.image)
   }

   override fun getItemCount(): Int {
     return items.size
   }
   fun updateitems( updateditems :ArrayList<News>)
   {
      items.clear()
      items.addAll(updateditems)
      notifyDataSetChanged()
   }

}

class NewsViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
   val titleview:TextView = itemView.findViewById(R.id.textView)
   val image:ImageView = itemView.findViewById(R.id.image)
   val author:TextView = itemView.findViewById(R.id.author)
}
interface Newsitemclicked{
   fun onitemclicked(item: News)
}