package berdnikov.kotlinexample.business

import berdnikov.kotlinexample.dagger.scope.ActivityScope
import berdnikov.kotlinexample.data.repository.INewsRepository
import com.google.gson.JsonElement
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Dmitry Berdnikov.
 */
@ActivityScope
class NewsInteractor @Inject constructor(private val newsRepository: INewsRepository) : INewsInteractor {

    override fun getNews(): Flowable<JsonElement> {
        return newsRepository.getNews()
    }

}