package com.arun.geeksforgeektask.Network

import android.util.Log
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.arun.geeksforgeektask.Model.ResponseModel

import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.schedulers.Schedulers


/**
// Created by Arun Singh Rawat  on 28-03-2021.



 **/

class FeedViewModel (var apiInterface: ApiInterface) : ViewModel() {
    var mediatorList = MediatorLiveData<ResponseModel>()
    fun getFeedList() {
        var list = LiveDataReactiveStreams.fromPublisher(
            apiInterface.fetchDetails("https://www.abc.net.au/news/feed/4570058/rss.xml")!!.onErrorReturn {
                ResponseModel().apply {
                    Log.e("error","--->>"+it.cause )
                    Log.e("error2","--->>" + it.message)

                }
            }.map {
                return@map try {
                    Log.e("getFeedList","->> "+ GsonBuilder().create().toJson(it))
                    it
                } catch (e: Exception) {
                    it

                }
            }.subscribeOn(Schedulers.io())
        )
        mediatorList.addSource(list){
                t ->
            mediatorList.value = t
            mediatorList.removeSource(list)

        } }
}