package berdnikov.kotlinexample.ui.news.di

import berdnikov.kotlinexample.business.INewsInteractor
import berdnikov.kotlinexample.business.NewsInteractor
import berdnikov.kotlinexample.dagger.scope.ActivityScope
import berdnikov.kotlinexample.data.repository.INewsRepository
import berdnikov.kotlinexample.data.repository.NewsRepository
import dagger.Binds
import dagger.Module

/**
 * Created by Dmitry Berdnikov.
 */
@Module
interface NewsModule {
    @Binds @ActivityScope
    fun provideNewRepository(newsRepository: NewsRepository): INewsRepository

    @Binds @ActivityScope
    fun provideNewsInteractor(newsInteractor: NewsInteractor): INewsInteractor

}