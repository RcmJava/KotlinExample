package berdnikov.kotlinexample.dagger.module

import berdnikov.kotlinexample.BuildConfig
import berdnikov.kotlinexample.network.RestApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Dmitry Berdnikov.
 */
@Module
class NetworkModule {
    companion object {
        val BASE_URL = "https://api.tinkoff.ru/v1/"
    }

    @Provides @Singleton
    fun provideApi(okHttpClient: OkHttpClient) : RestApi {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .validateEagerly(BuildConfig.DEBUG)
                .build()
                .create(RestApi::class.java)
    }

    @Provides @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
                .build()
    }

}