package berdnikov.kotlinexample.data.repository

import com.google.gson.JsonElement
import io.reactivex.Flowable

/**
 * Created by Dmitry Berdnikov.
 */
interface INewsRepository {
    fun getNews(): Flowable<JsonElement>
}