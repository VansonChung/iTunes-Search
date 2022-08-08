package com.van.itunessearch.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.van.itunessearch.R
import com.van.itunessearch.viewmodel.SearchViewModel
import com.van.itunessearch.viewmodel.SearchViewModelFactory
import timber.log.Timber

abstract class TabFragment : Fragment(), MenuProvider {

    protected val searchViewModel: SearchViewModel by activityViewModels { SearchViewModelFactory() }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        if (isResumed) { // avoid two searchView ...
            menuInflater.inflate(R.menu.menu_options, menu)
            val searchItem = menu.findItem(R.id.search)
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(onQueryTextListener)
        }
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?): Boolean {
            Timber.d("query : $query")
            query?.let {
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