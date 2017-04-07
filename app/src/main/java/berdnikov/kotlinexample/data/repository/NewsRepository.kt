package berdnikov.kotlinexample.data.repository

import berdnikov.kotlinexample.dagger.scope.ActivityScope
import berdnikov.kotlinexample.network.RestApi
import com.google.gson.JsonElement
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Dmitry Berdnikov.
 */
@ActivityScope
class NewsRepository @Inject constructor(private val restApi: RestApi): INewsRepository {

    override fun getNews(): Flowable<JsonElement> {
        return restApi.getNews()
    }

}