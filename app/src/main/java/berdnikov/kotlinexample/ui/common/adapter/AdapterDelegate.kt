package berdnikov.kotlinexample.ui.common.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Dmitry Berdnikov.
 */
abstract class AdapterDelegate<in T> {

    abstract fun isForViewType(items: List<T>, position: Int): Boolean

    abstract fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder

    abstract fun onBindViewHolder(items: List<T>, position: Int, holder: RecyclerView.ViewHolder, payloads: List<Any>)

    fun onViewRecycled(holder: RecyclerView.ViewHolder) {}

    fun onFailedToRecycleView(holder: RecyclerView.ViewHolder) : Boolean = false

    fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {}

    fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {}
}