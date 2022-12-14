package com.van.itunessearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import com.van.itunessearch.DataRepository
import com.van.itunessearch.R
import com.van.itunessearch.databinding.FragmentTabBinding
import com.van.itunessearch.ui.adapter.MediaAdapter
import com.van.itunessearch.viewmodel.ApiStatus
import com.van.itunessearch.viewmodel.MediaInfo
import com.van.itunessearch.viewmodel.SearchViewModel
import timber.log.Timber

open class TabFragment : Fragment(), MenuProvider {

    private var _binding: FragmentTabBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchView: SearchView

    // activityViewModels -> fragment sharing (viewModel)
    protected val searchViewModel: SearchViewModel by activityViewModels {
        SearchViewModel.Factory(
            DataRepository
        )
    }

    private val progressDialogFragment by lazy {
        ProgressDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView")
        _binding = FragmentTabBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = MediaAdapter()
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        if (isResumed) { // avoid two SearchView ...
            menuInflater.inflate(R.menu.menu_options, menu)
            val searchItem = menu.findItem(R.id.search)
            searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(onQueryTextListener)
        }
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }

    override fun onDestroyView() {
        Timber.d("onDestroyView")
        super.onDestroyView()
        _binding = null
    }

    protected fun apiStatusHandle(status: ApiStatus) {
        when (status) {
            ApiStatus.Loading -> {
                val adapter = binding.recyclerView.adapter as MediaAdapter
                adapter.submitList(null)
                binding.textView.visibility = View.GONE
                binding.loading.visibility = View.VISIBLE
            }
            is ApiStatus.Error -> {
                binding.loading.visibility = View.GONE
                binding.textView.visibility = View.VISIBLE
                binding.textView.text = status.msg
            }
            ApiStatus.Done -> {
                binding.textView.visibility = View.GONE
                binding.loading.visibility = View.GONE
            }
            else -> {}
        }
    }

    protected fun submitAdapterData(data: List<MediaInfo>) {
        val adapter = binding.recyclerView.adapter as MediaAdapter
        adapter.submitList(data)
        binding.recyclerView.visibility = View.VISIBLE
    }

    protected fun showProgressDialogFragment(show: Boolean) {
        if (show) {
            progressDialogFragment.show(childFragmentManager, ProgressDialogFragment.TAG)
        } else {
            progressDialogFragment.dialog?.let { if (it.isShowing) progressDialogFragment.dismiss() }
        }
    }

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?): Boolean {
            Timber.d("query : $query")
            query?.let {
                searchView.clearFocus()
                searchViewModel.searchMusic(query)
                searchViewModel.searchMovie(query)
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    }
}