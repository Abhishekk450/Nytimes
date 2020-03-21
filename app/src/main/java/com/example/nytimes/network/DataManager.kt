package com.example.nytimes.network

import com.example.nytimes.model.NetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataManager {
    private val BASE_URL = "https://api.nytimes.com/"
    private var retrofit: Retrofit? = null
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(NetworkInterceptor())
        .addInterceptor(interceptor)
        .build()

    fun getRetrofitInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit!!
    }
}