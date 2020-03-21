package com.example.nytimes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.nytimes.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : AppCompatActivity() {
    var result: Result?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        actionBar?.hide()
        getIntentData()
    }

    fun getIntentData() {
        if (intent.hasExtra("DATA")) {
            result = intent?.getSerializableExtra("DATA") as Result
            setData(result!!)
        }
    }

    fun setData(result: Result) {
        if (result != null) {
            Picasso.get().load(result?.multimedia?.get(4)?.url).resize(270, 120).into(image_news)
            text_news_title.text = result?.title
            text_description.text = result?.abstract
            text_link.text = result?.url
        } else {
            text_no_data_found.visibility = View.VISIBLE
        }
    }
}
