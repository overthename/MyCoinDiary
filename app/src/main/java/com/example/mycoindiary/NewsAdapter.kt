package com.example.mycoindiary

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.newsitem.view.*


class NewsAdapter (var items:ArrayList<NewsItem>,var context:Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.newsitem,parent,false)
            return ViewHolder(itemView)
     }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = items[position]
        holder.setItem(item)
        holder.itemView.setOnClickListener {

            val openURL = Intent(android.content.Intent.ACTION_VIEW)
           openURL.data = Uri.parse(items.get(position).url)
            startActivity(context, openURL, null)

        }

    }

    override fun getItemCount()= items.count()


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    fun setItem(item:NewsItem){
        itemView.newsname.text = item.title
        itemView.newsabout.text = item.about

    }}
}