package berdnikov.kotlinexample.dagger.component

import berdnikov.kotlinexample.dagger.ComponentManager
import berdnikov.kotlinexample.dagger.module.NetworkModule
import berdnikov.kotlinexample.ui.news.di.NewsComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Dmitry Berdnikov.
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface AppComponent {
    fun inject(componentManager: ComponentManager)

    fun newsComponentBuilder(): NewsComponent.Builder
}