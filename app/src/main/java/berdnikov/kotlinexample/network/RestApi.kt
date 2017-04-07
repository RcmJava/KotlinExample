package berdnikov.kotlinexample.network

import com.google.gson.JsonElement
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Created by Dmitry Berdnikov.
 */
interface RestApi {

    @GET("news")
    fun getNews() : Flowable<JsonElement>
}