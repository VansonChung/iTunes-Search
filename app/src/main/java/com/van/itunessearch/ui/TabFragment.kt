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
import com.van.itunessearch.R
import com.van.itunessearch.databinding.FragmentTabBinding
import com.van.itunessearch.viewmodel.SearchViewModel
import com.van.itunessearch.viewmodel.SearchViewModelFactory
import timber.log.Timber

open class TabFragment : Fragment(), MenuProvider {

    private var _binding: FragmentTabBinding? = null
    protected val binding get() = _binding!!

    private lateinit var searchView: SearchView

    protected val searchViewModel: SearchViewModel by activityViewModels { SearchViewModelFactory() }

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