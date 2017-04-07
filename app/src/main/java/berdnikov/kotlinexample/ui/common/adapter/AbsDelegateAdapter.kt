package berdnikov.kotlinexample.ui.common.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Dmitry Berdnikov.
 */
abstract class AbsDelegateAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val delegatesManager: AdapterDelegatesManager<T> = AdapterDelegatesManager()
    var items: MutableList<T> = mutableListOf()

    private lateinit var layoutInflater: LayoutInflater

    // >>> Overriding RecyclerView's methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(items, position, holder, null)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>?) {
        delegatesManager.onBindViewHolder(items, position, holder, payloads)
    }

    override fun getItemCount(): Int = items.size

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        delegatesManager.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return delegatesManager.onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        delegatesManager.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        delegatesManager.onViewDetachedFromWindow(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(recyclerView?.context)
        }
        delegatesManager.setLayoutInflater(layoutInflater)
    }
    // <<< Overriding RecyclerView's methods

    // >>> Methods for work with items
    fun getItem(position: Int) : T = items[position]

    fun add(position: Int, item: T) {
        items.add(position, item)
        notifyItemInserted(position)
        val positionStart = position
        val itemCount = items.size - position
        notifyItemRangeChanged(positionStart, itemCount)
    }

    fun add(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addAll(items: List<T>) {
        val size = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(size, items.size)
    }

    fun addAll(position: Int, items: List<T>) {
        this.items.addAll(position, items)
        notifyItemRangeInserted(position, items.size)
    }

    fun set(position: Int, item: T) {
        items[position] = item
        notifyItemChanged(position)
    }

    fun clear() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun clearRange(startPosition: Int) {
        val size = items.size
        items.subList(startPosition, size).clear()
        notifyItemRangeRemoved(startPosition, size)
    }
    // <<< Methods for work with items
}