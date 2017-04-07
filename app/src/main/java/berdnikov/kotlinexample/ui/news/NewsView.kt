package berdnikov.kotlinexample.ui.news

import com.arellomobile.mvp.MvpView

/**
 * Created by Dmitry Berdnikov.
 */
interface NewsView : MvpView {
    fun hideProgress()
    fun setData()
    fun showError(error: Throwable)
}