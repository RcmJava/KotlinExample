package berdnikov.kotlinexample

import android.app.Application
import berdnikov.kotlinexample.dagger.ComponentManager

/**
 * Created by Dmitry Berdnikov.
 */
class App : Application() {

    private lateinit var componentManager: ComponentManager

    override fun onCreate() {
        super.onCreate()
        componentManager = ComponentManager(this)
    }

    fun componentManager(): ComponentManager = componentManager
}