package com.van.itunessearch.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.van.itunessearch.R

abstract class TabFragment : Fragment() {

    abstract fun onSearch(query: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        if (isResumed) { // avoid two searchView ...
            menuInflater.inflate(R.menu.menu_options, menu)
            val searchItem = menu.findItem(R.id.search)
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(onQueryTextListener)
        }
    }

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let { onSearch(it) }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }

    }
}