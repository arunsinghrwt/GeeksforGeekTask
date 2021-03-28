package com.arun.geeksforgeektask.Adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arun.geeksforgeektask.Model.ItemList
import com.arun.geeksforgeektask.R
import com.arun.geeksforgeektask.Utility.GlideRequests
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

import kotlinx.android.synthetic.main.feed_row_layout.view.*
import java.text.SimpleDateFormat
import java.util.*


/**
// Created by Arun Singh Rawat  on 28-03-2021.



 **/

class NewsFeedAdapter(
    var itemList: ArrayList<ItemList>,
    var context: Context,
    var glideRequests: GlideRequests
) : RecyclerView.Adapter<NewsFeedAdapter.ViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(context)
            .inflate(R.layout.feed_row_layout, null)
        return ViewHolder(itemLayoutView)
    }
    override fun getItemCount(): Int {
        return itemList.size
    }

    fun removeList(){
        val leadStatusItem = ItemList()
        itemList.removeAll(listOf(leadStatusItem))
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemView.title.text = item.title

        var date = mDateConvert(item.pubDate, "date")
        var time = mDateConvert(item.pubDate, "time")

        holder.itemView.date.text = date
        holder.itemView.time.text = time
        //image
        val url = item.thumbnail!!
        val newUrl= url.substringBefore("?")
        glideRequests.load(newUrl)
            .placeholder(ColorDrawable(Color.GRAY))
            .error(ColorDrawable(Color.GRAY))
            .thumbnail(glideRequests.load(newUrl).override(300).transform(RoundedCorners(12)))
            .transform(RoundedCorners(12))
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.itemView.thumb)



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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}


