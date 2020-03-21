package com.example.nytimes.network

import com.example.nytimes.model.NewsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("svc/topstories/v2/world.json")
    fun getNewsData(@Query("api-key") apiKey: String): Call<NewsList>
}
