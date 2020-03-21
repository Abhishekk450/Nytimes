package com.example.nytimes.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimes.NewsDetailsActivity
import com.example.nytimes.R
import com.example.nytimes.model.Result
import com.example.nytimes.utils.Utils
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news_feed.view.*

class NyAdapter(var activity: Activity, var newsList:ArrayList<Result>): RecyclerView.Adapter<NyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =  LayoutInflater.from(activity).inflate(R.layout.item_news_feed, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(newsList?.get(position)?.multimedia?.get(0)?.url).resize(90,90).into(holder?.imageThumbnail, object :
            Callback {
            override fun onSuccess() {
                holder?.progressBar?.setVisibility(View.GONE)
            }

            override fun onError(e: Exception) {
                holder?.progressBar?.setVisibility(View.GONE)
            }
        })

        holder.titleNews.text = newsList.get(position).title
        holder.newDescription.text = newsList.get(position).abstract
        holder.textCalendar.text = Utils.checkIsCurrentDate(newsList.get(position).published_date)
        holder.constraintRoot.setOnClickListener { v: View? ->
            var intent = Intent(activity, NewsDetailsActivity::class.java)
            intent.putExtra("DATA", newsList[position])
            activity.startActivity(intent)
        }
    }

        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            var constraintRoot = view.findViewById<ConstraintLayout>(R.id.constraint_root)
            var imageThumbnail = view.findViewById<ImageView>(R.id.image_thumbnail)
            var titleNews  = view.findViewById<TextView>(R.id.text_news_title)
            var newDescription = view.findViewById<TextView>(R.id.text_news_description)
            var progressBar = view.progressBar
            var textCalendar = view.findViewById<TextView>(R.id.text_calendar)
        }
}