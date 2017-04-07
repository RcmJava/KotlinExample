package berdnikov.kotlinexample.ui.common.view

import android.os.Bundle
import berdnikov.kotlinexample.App
import berdnikov.kotlinexample.dagger.ComponentManager
import com.arellomobile.mvp.MvpAppCompatActivity

/**
 * Created by Dmitry Berdnikov.
 */
abstract class BaseActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        doInjection()
        super.onCreate(savedInstanceState)
    }

    protected abstract fun doInjection()

    protected fun getComponentManager() : ComponentManager {
        val appContext = applicationContext as App
        return appContext.componentManager()
    }
}