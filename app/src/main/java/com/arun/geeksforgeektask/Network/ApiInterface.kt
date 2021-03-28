package com.arun.geeksforgeektask.Network

import com.arun.geeksforgeektask.Model.ResponseModel
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


/**
// Created by Arun Singh Rawat  on 28-03-2021.



 **/

interface ApiInterface {

    //get news feed
    @GET("/v1/api.json")
    @Headers("Content-Type:application/json")
    fun fetchDetails(@Query("rss_url") rss_url: String?
    ): Flowable<ResponseModel>?

}