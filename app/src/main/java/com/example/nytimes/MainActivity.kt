package com.example.nytimes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytimes.adapter.NyAdapter
import com.example.nytimes.model.ErrorHandler
import com.example.nytimes.model.NetworkInterceptor
import com.example.nytimes.model.NetworkInterceptor.NoConnectivityException
import com.example.nytimes.model.NewsList
import com.example.nytimes.model.Result
import com.example.nytimes.network.ApiInterface
import com.example.nytimes.network.DataManager
import com.example.nytimes.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var newsList = ArrayList<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNewsData()
    }

    fun getNewsData() {

        var apiInteface = DataManager.getRetrofitInstance().create(ApiInterface::class.java)

        var call: Call<NewsList> = apiInteface.getNewsData(resources.getString(R.string.api_key))
        showProgress()

        call.enqueue(object : Callback<NewsList?> {
            override fun onFailure(call: Call<NewsList?>, t: Throwable) {
             Utils.showError(this@MainActivity,constraint_main,null )
            }

            override fun onResponse(
                call: Call<NewsList?>,
                response: Response<NewsList?>
            ) {
                hideProgress()
                generateNewsList(response?.body()?.results as ArrayList<Result>)
            }
        })
    }


    fun showProgress() {
        progress_bar.setVisibility(View.VISIBLE)
    }

    fun hideProgress() {
        progress_bar.setVisibility(View.GONE)
    }

    fun generateNewsList(newsList: ArrayList<Result>) {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view?.adapter = NyAdapter(this, newsList)
    }
}
