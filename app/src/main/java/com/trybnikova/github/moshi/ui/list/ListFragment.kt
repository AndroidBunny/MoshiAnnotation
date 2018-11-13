package com.trybnikova.github.moshi.ui.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.trybnikova.github.moshi.R
import com.trybnikova.github.moshi.databinding.FragmentListBinding
import com.trybnikova.github.moshi.ui.list.adapter.TaskListItemAdapter
import kotlinx.android.synthetic.main.fragment_list.listRv
import kotlinx.android.synthetic.main.fragment_list.listSwipeToRefresh
import kotlinx.android.synthetic.main.fragment_list.listToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Displays tasks in a RecyclerView.
 */
class ListFragment : Fragment() {

    private lateinit var adapter: TaskListItemAdapter

    private val listVm by viewModel<ListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = TaskListItemAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        // Data binding
        val binding = DataBindingUtil.inflate<FragmentListBinding>(
            inflater,
            R.layout.fragment_list,
            container,
            false
        )
        binding.listVm = listVm
        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar
        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.setSupportActionBar(listToolbar)

        listRv.layoutManager = LinearLayoutManager(context)
        listRv.adapter = adapter

        listSwipeToRefresh.setOnRefreshListener {
            listVm.loadTasksList()
        }
        listSwipeToRefresh.setColorSchemeResources(R.color.red, R.color.blue)

        listVm.loadTasksList()

        listVm.taskListItems.observe(this, Observer { taskListItems ->
            adapter.items = taskListItems
            adapter.notifyDataSetChanged()
        })

        listVm.listState.observe(this, Observer { state ->
            state?.let {
                when (it) {
                    ListState.LOADING -> listSwipeToRefresh.isRefreshing = true
                    else -> listSwipeToRefresh.isRefreshing = false
                }
            }
        })
    }

}
