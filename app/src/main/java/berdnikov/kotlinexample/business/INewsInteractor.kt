package berdnikov.kotlinexample.business

import com.google.gson.JsonElement
import io.reactivex.Flowable

/**
 * Created by Dmitry Berdnikov.
 */
interface INewsInteractor {
    fun getNews() : Flowable<JsonElement>
}