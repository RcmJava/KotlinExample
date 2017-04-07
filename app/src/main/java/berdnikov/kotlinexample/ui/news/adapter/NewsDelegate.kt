package berdnikov.kotlinexample.ui.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import berdnikov.kotlinexample.R
import berdnikov.kotlinexample.ui.common.adapter.AdapterDelegate
import berdnikov.kotlinexample.ui.common.entity.DisplayableItem

/**
 * Created by Dmitry Berdnikov.
 */
class NewsDelegate : AdapterDelegate<DisplayableItem>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsHolder(layoutInflater.inflate(R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(items: List<DisplayableItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: List<Any>) {

    }
}