package berdnikov.kotlinexample.dagger

import android.app.Application
import berdnikov.kotlinexample.dagger.component.AppComponent
import berdnikov.kotlinexample.dagger.component.DaggerAppComponent
import berdnikov.kotlinexample.ui.news.di.NewsComponent

/**
 * Created by Dmitry Berdnikov.
 */
class ComponentManager(application: Application) {
    private val appComponent: AppComponent = DaggerAppComponent.builder()
            .build()

    fun newsComponent(): NewsComponent {
        return appComponent
                .newsComponentBuilder()
                .build()
    }
}