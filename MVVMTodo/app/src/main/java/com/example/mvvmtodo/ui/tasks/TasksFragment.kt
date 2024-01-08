package com.example.mvvmtodo.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmtodo.R
import com.example.mvvmtodo.databinding.FragmentTasksBinding
import com.example.mvvmtodo.util.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment() {
    private lateinit var fragmentTasksBinding: FragmentTasksBinding
    private val viewModel: TasksViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTasksBinding = FragmentTasksBinding.inflate(inflater, container, false)
        return fragmentTasksBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskAdapter = TasksAdapter()

        fragmentTasksBinding.apply {
            recyclerViewTasks.apply {
                adapter = taskAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.tasks.observe(viewLifecycleOwner) {
            taskAdapter.submitList(it)
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_fragment_tasks, menu)

                val searchItem = menu.findItem(R.id.action_search)
                val searchView = searchItem.actionView as SearchView

                searchView.onQueryTextChanged {
                    viewModel.searchQuery.value = it
                }
            }

            override fun onMenuItemSelected(item: MenuItem): Boolean {
                return when (item.itemId) {
                    R.id.action_sort_by_name -> {
                        viewModel.sortOrder.value = SortOrder.BY_NAME
                        true
                    }

                    R.id.action_sort_by_date_created -> {
                        viewModel.sortOrder.value = SortOrder.BY_DATE
                        true
                    }

                    R.id.action_hide_completed_tasks -> {
                        item.isChecked = !item.isChecked
                        viewModel.hideCompleted.value = item.isChecked
                        true
                    }

                    R.id.action_delete_all_completed_tasks -> {
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}