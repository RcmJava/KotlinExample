package berdnikov.kotlinexample.ui.news.di

import berdnikov.kotlinexample.dagger.scope.ActivityScope
import berdnikov.kotlinexample.ui.news.NewsActivity
import dagger.Subcomponent

/**
 * Created by Dmitry Berdnikov.
 */
@Subcomponent(modules = arrayOf(NewsModule::class))
@ActivityScope
interface NewsComponent {
    @Subcomponent.Builder interface Builder {
        fun build(): NewsComponent
    }

    fun inject(activity: NewsActivity)
}