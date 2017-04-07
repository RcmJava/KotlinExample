package berdnikov.kotlinexample.ui.common.view

import android.os.Bundle
import android.support.annotation.LayoutRes
import berdnikov.kotlinexample.App
import berdnikov.kotlinexample.dagger.ComponentManager
import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Created by Dmitry Berdnikov.
 */
abstract class BaseFragment: MvpAppCompatFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        doInjections()
        super.onCreate(savedInstanceState)
    }

    protected abstract fun doInjections()

  /*  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, getLayoutRes(), container, false);
        return binding.root
    }
*/
    @LayoutRes protected abstract fun getLayoutRes(): Int

    protected fun getComponentManager() : ComponentManager {
        val appContext = context.applicationContext as App
        return appContext.componentManager()
    }
}