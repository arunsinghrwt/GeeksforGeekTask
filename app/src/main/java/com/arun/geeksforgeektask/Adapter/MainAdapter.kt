package com.arun.geeksforgeektask.Adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arun.geeksforgeektask.Model.ItemList
import com.arun.geeksforgeektask.R
import com.arun.geeksforgeektask.Utility.GlideRequests
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.text.SimpleDateFormat
import java.util.ArrayList


/**
// Created by Arun Singh Rawat on 29-03-2021.



 **/

class MainAdapter(var itemList: ArrayList<ItemList>,
                  var context: Context,
                  var glideRequests: GlideRequests
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.e("viewType", "--->>>$viewType")
        if (viewType == VIEW_TYPE_TOP) {
            return ViewTopViewHolder(
                LayoutInflater.from(context).inflate(R.layout.feed_top_row_layout, parent, false)
            )
        }
        return ViewNormalViewHolder(
            LayoutInflater.from(context).inflate(R.layout.feed_row_layout, parent, false)
        )
    }


    fun removeList(){
        val leadStatusItem = ItemList()
        itemList.removeAll(listOf(leadStatusItem))
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0){
            (holder as ViewTopViewHolder).bind(position)
        }
        else{
            (holder as ViewNormalViewHolder).bind(position)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewNormalViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
         var title: TextView = itemView.findViewById(R.id.title)
         var date: TextView = itemView.findViewById(R.id.date)
         var time: TextView = itemView.findViewById(R.id.time)
         var image: ImageView = itemView.findViewById(R.id.thumb)
        fun bind(position: Int) {
            val list = itemList[position]
            title.text = list.title
            var mDate: String = mDateConvert(list.pubDate, "date")
            var mTime = mDateConvert(list.pubDate, "time")
            date.text = mDate
            time.text = mTime
            val url = list.thumbnail!!
            val newUrl= url.substringBefore("?")
            imageLoader(newUrl,image)

        }
    }

     inner class ViewTopViewHolder(itemView: View) :
         RecyclerView.ViewHolder(itemView) {
         var title: TextView = itemView.findViewById(R.id.title_top)
         var date: TextView = itemView.findViewById(R.id.date_top)
         var time: TextView = itemView.findViewById(R.id.time_top)
         var image: ImageView = itemView.findViewById(R.id.thumb_top)
         fun bind(position: Int) {
             val list = itemList[position]
             title.text = list.title
             var mDate: String = mDateConvert(list.pubDate, "date")
             var mTime = mDateConvert(list.pubDate, "time")
             date.text = mDate
             time.text = mTime
             val url = list.enclosure.link!!
             val newUrl= url.substringBefore("?")
             imageLoader(newUrl,image)

         }
    }



    private fun imageLoader(url: String, image: ImageView) {
        glideRequests.load(url)
            .placeholder(ColorDrawable(Color.GRAY))
            .error(ColorDrawable(Color.GRAY))
            .thumbnail(glideRequests.load(url).override(300).transform(RoundedCorners(12)))
            .transform(RoundedCorners(12))
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(image)
    }

    private fun mDateConvert(pubDate: String?, s: String): String {
        var dateStr = pubDate
        try {
            val srcDf = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
            val date = srcDf.parse(dateStr)
            if (s == "date"){
                val destDf = SimpleDateFormat("MMM dd, yyyy")
                dateStr = destDf.format(date)
            }else if (s == "time"){
                val destDt = SimpleDateFormat("hh:mm aa")
                dateStr = destDt.format(date)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return  dateStr!!
    }


    companion object{
        const val VIEW_TYPE_TOP = 0

    }
}