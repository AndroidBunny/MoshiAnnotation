package com.trybnikova.github.moshi.ui.list.adapter

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.trybnikova.github.moshi.ui.list.adapter.delegate.TaskItemDelegate

/**
 * Adapter for the RecyclerView in [com.trybnikova.github.moshi.ui.list.ListFragment]
 */
class TaskListItemAdapter : ListDelegationAdapter<List<ListItem>>() {

    init {
        delegatesManager.apply {
            addDelegate(TaskItemDelegate())
        }
    }
}
