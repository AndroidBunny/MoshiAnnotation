package com.trybnikova.github.moshi.ui.list


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trybnikova.github.moshi.rest.TaskRepository
import com.trybnikova.github.moshi.ui.list.adapter.ListItem
import com.trybnikova.github.moshi.ui.list.adapter.TaskItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * ViewModel for the [ListFragment] that loads a list of tasks.
 */
class ListViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    val taskListItems = MutableLiveData<List<ListItem>>()
    val listState = MutableLiveData<ListState>()

    private val disposables = CompositeDisposable()

    /**
     * Loads tasks and updates [taskListItems]
     */
    fun loadTasksList() {
        listState.value = ListState.LOADING
        taskRepository.getTasks()
            .map { tasks ->
                val taskItems = mutableListOf<TaskItem>()
                tasks.taskList?.let {
                    it.mapTo(taskItems) { task -> task.mapToTaskItem() }
                }
                taskItems
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { taskItems ->
                    taskListItems.value = taskItems
                    if (taskItems.isEmpty()) {
                        listState.value = ListState.EMPTY_LIST
                    } else {
                        listState.value = ListState.READY
                    }
                },
                onError = { t ->
                    Timber.e(t)
                    listState.value = ListState.ERROR
                }
            ).addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}

