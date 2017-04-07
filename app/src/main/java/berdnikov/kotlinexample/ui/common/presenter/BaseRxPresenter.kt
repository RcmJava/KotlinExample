package berdnikov.kotlinexample.ui.common.presenter

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Dmitry Berdnikov.
 */
abstract class BaseRxPresenter<V: MvpView>: MvpPresenter<V>() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun unsubscribeOnUnbindView(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}