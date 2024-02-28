package pl.romzes.wallpaperfinder.app

import android.app.Application
import android.util.Log
import pl.romzes.wallpaperfinder.di.AppComponent
import pl.romzes.wallpaperfinder.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()

    }
}