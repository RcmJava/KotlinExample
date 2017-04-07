package berdnikov.kotlinexample.ui.common.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.*

/**
 * Created by Dmitry Berdnikov.
 */
class AdapterDelegatesManager<T> {
    internal val FALLBACK_DELEGATE_VIEW_TYPE = Integer.MAX_VALUE - 1
    internal val PAYLOADS_EMPTY_LIST = Collections.emptyList<Any>()

    protected var delegates = SparseArrayCompat<AdapterDelegate<T>>()
    protected var fallbackDelegate: AdapterDelegate<T>? = null

    private lateinit var layoutInflater: LayoutInflater

    fun setLayoutInflater(layoutInflater: LayoutInflater) {
        this.layoutInflater = layoutInflater
    }

    fun addDelegate(delegate: AdapterDelegate<T>): AdapterDelegatesManager<T> {
        var viewType = delegates.size()
        while (delegates.get(viewType) != null) {
            viewType++
            if (viewType == FALLBACK_DELEGATE_VIEW_TYPE) {
                throw IllegalArgumentException(
                        "Oops, we are very close to Integer.MAX_VALUE. It seems that there are no more free and unused view action integers left to add another AdapterDelegate.")
            }
        }
        return addDelegate(viewType, false, delegate)
    }

    fun addDelegate(viewType: Int, allowReplacingDelegate: Boolean = false,
                    delegate: AdapterDelegate<T>): AdapterDelegatesManager<T> {
        /*if (delegate == null) {
            throw NullPointerException("AdapterDelegate is null!")
        }
*/
        if (viewType == FALLBACK_DELEGATE_VIEW_TYPE) {
            throw IllegalArgumentException("The view action = "
                    + FALLBACK_DELEGATE_VIEW_TYPE
                    + " is reserved for fallback adapter delegate (see setFallbackDelegate() ). Please use another view action.")
        }

        if (!allowReplacingDelegate && delegates.get(viewType) != null) {
            throw IllegalArgumentException(
                    "An AdapterDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered AdapterDelegate is "
                            + delegates.get(viewType))
        }
        delegates.put(viewType, delegate)
        return this
    }

    fun getItemViewType(items: List<T>, position: Int): Int {

       /* if (items == null) {
            throw NullPointerException("Items datasource is null!")
        }*/

        val delegatesCount = delegates.size()
        for (i in 0..delegatesCount - 1) {
            val delegate = delegates.valueAt(i)
            if (delegate.isForViewType(items, position)) {
                return delegates.keyAt(i)
            }
        }

        if (fallbackDelegate != null) {
            return FALLBACK_DELEGATE_VIEW_TYPE
        }

        throw NullPointerException(
                "No AdapterDelegate added that matches position=$position in data source")
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val delegate = getDelegateForViewType(viewType) ?: throw NullPointerException("No AdapterDelegate added for ViewType " + viewType)

        val vh = delegate.onCreateViewHolder(layoutInflater, parent)
        return vh
    }

    fun onBindViewHolder(items: List<T>, position: Int,
                         viewHolder: RecyclerView.ViewHolder, payloads: List<Any>?) {

        val delegate = getDelegateForViewType(viewHolder.itemViewType) ?: throw NullPointerException("No delegate found for item at position = "
                + position
                + " for viewType = "
                + viewHolder.itemViewType)
        delegate.onBindViewHolder(items, position, viewHolder, payloads!!)
    }

    fun onBindViewHolder(items: List<T>, position: Int,
                         viewHolder: RecyclerView.ViewHolder) {
        onBindViewHolder(items, position, viewHolder, PAYLOADS_EMPTY_LIST)
    }

    fun onViewRecycled(viewHolder: RecyclerView.ViewHolder) {
        val delegate = getDelegateForViewType(viewHolder.itemViewType) ?: throw NullPointerException("No delegate found for "
                + viewHolder
                + " for item at position = "
                + viewHolder.adapterPosition
                + " for viewType = "
                + viewHolder.itemViewType)
        delegate.onViewRecycled(viewHolder)
    }

    fun onFailedToRecycleView(viewHolder: RecyclerView.ViewHolder): Boolean {
        val delegate = getDelegateForViewType(viewHolder.itemViewType) ?: throw NullPointerException("No delegate found for "
                + viewHolder
                + " for item at position = "
                + viewHolder.adapterPosition
                + " for viewType = "
                + viewHolder.itemViewType)
        return delegate.onFailedToRecycleView(viewHolder)
    }

    fun onViewAttachedToWindow(viewHolder: RecyclerView.ViewHolder) {
        val delegate = getDelegateForViewType(viewHolder.itemViewType) ?: throw NullPointerException("No delegate found for "
                + viewHolder
                + " for item at position = "
                + viewHolder.adapterPosition
                + " for viewType = "
                + viewHolder.itemViewType)
        delegate.onViewAttachedToWindow(viewHolder)
    }

    fun onViewDetachedFromWindow(viewHolder: RecyclerView.ViewHolder) {
        val delegate = getDelegateForViewType(viewHolder.itemViewType) ?: throw NullPointerException("No delegate found for "
                + viewHolder
                + " for item at position = "
                + viewHolder.adapterPosition
                + " for viewType = "
                + viewHolder.itemViewType)
        delegate.onViewDetachedFromWindow(viewHolder)
    }

    fun getViewType(delegate: AdapterDelegate<T>): Int {
       /* if (delegate == null) {
            throw NullPointerException("Delegate is null")
        }
*/
        val index = delegates.indexOfValue(delegate)
        if (index == -1) {
            return -1
        }
        return delegates.keyAt(index)
    }

    fun getDelegateForViewType(viewType: Int): AdapterDelegate<T>? {
        val delegate = delegates.get(viewType) ?: if (fallbackDelegate == null) {
            return null
        } else {
            return fallbackDelegate
        }

        return delegate
    }
}