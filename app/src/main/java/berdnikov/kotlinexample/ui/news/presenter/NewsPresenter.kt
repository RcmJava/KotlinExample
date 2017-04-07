package berdnikov.kotlinexample.ui.news.presenter

import berdnikov.kotlinexample.business.INewsInteractor
import berdnikov.kotlinexample.dagger.scope.ActivityScope
import berdnikov.kotlinexample.ui.common.presenter.BaseRxPresenter
import berdnikov.kotlinexample.ui.news.NewsView
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Dmitry Berdnikov.
 */
@ActivityScope
@InjectViewState
class NewsPresenter @Inject constructor(
        private val newsInteractor: INewsInteractor
) : BaseRxPresenter<NewsView>() {

    fun loadData(pullToRefresh: Boolean) {
        val subscribtion = newsInteractor.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            element ->
                                viewState.hideProgress()
                                viewState.setData()
                        },
                        {
                            error ->
                                viewState.showError(error)
                        })

        unsubscribeOnUnbindView(subscribtion)
    }
}