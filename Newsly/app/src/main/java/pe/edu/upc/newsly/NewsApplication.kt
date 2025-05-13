package pe.edu.upc.newsly

import android.app.Application

class NewsApplication : Application() {

    companion object {
        lateinit var instance: NewsApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}