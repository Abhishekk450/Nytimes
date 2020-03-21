package com.example.nytimes.model
import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {

        var userId: String = ""
        var isMessageVisible: Boolean = false
        var application: MyApplication? = null
            private set
    }
}
