package berdnikov.kotlinexample.ui.news

import android.os.Bundle
import berdnikov.kotlinexample.ui.common.view.BaseActivity
import berdnikov.kotlinexample.ui.news.presenter.NewsPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import javax.inject.Inject

/**
 * Created by Dmitry Berdnikov.
 */
class NewsActivity : BaseActivity(), NewsView {

    @Inject @InjectPresenter lateinit var presenter: NewsPresenter

    override fun doInjection() {
        getComponentManager()
                .newsComponent()
                .inject(this)
    }

    @ProvidePresenter fun presenter(): NewsPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.loadData(false)
    }

    override fun hideProgress() {

    }

    override fun setData() {

    }

    override fun showError(error: Throwable) {

    }
}