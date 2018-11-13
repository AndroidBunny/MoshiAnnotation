package com.trybnikova.github.moshi.ui.list.adapter.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import com.trybnikova.github.moshi.R
import com.trybnikova.github.moshi.ui.list.adapter.ListItem
import com.trybnikova.github.moshi.ui.list.adapter.TaskItem
import com.trybnikova.github.moshi.ui.utils.asDateNoWeekday


/**
 * Delegate for [TaskItem]s. Used in [com.trybnikova.github.moshi.ui.list.adapter.TaskListItemAdapter]
 */
class TaskItemDelegate : AbsListItemAdapterDelegate<TaskItem, ListItem, TaskItemDelegate.TaskItemViewHolder>() {

    override fun isForViewType(item: ListItem, items: List<ListItem>, position: Int): Boolean = item is TaskItem

    override fun onCreateViewHolder(parent: ViewGroup): TaskItemViewHolder =
        TaskItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_task,
                parent,
                false
            )
        )

    override fun onBindViewHolder(item: TaskItem, vh: TaskItemViewHolder, @Nullable payloads: List<Any>) {
        val ctx = vh.itemView.context
        val resources = vh.itemView.context.resources

        vh.idTv.text = item.taskId.toString()
        vh.taskIv.setBackgroundColor(
            if (item.isDone) ContextCompat.getColor(
                ctx,
                R.color.blue_20
            ) else ContextCompat.getColor(ctx, R.color.red_20)
        )

        vh.detailsTv.text =
                resources.getString(
                    R.string.item_description,
                    item.name,
                    item.date.asDateNoWeekday(),
                    item.location,
                    item.version
                )
    }

    /**
     * View holder for [TaskItemDelegate]
     */
    class TaskItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val taskIv: ImageView = itemView.findViewById(R.id.itemTaskIv)
        val idTv: TextView = itemView.findViewById(R.id.itemTaskIdTv)
        val detailsTv: TextView = itemView.findViewById(R.id.itemTaskDetailsTv)
    }
}
