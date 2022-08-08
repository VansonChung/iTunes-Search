package com.van.itunessearch.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import timber.log.Timber

class MovieFragment : TabFragment() {

    override fun onAttach(context: Context) {
        Timber.d("onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        initView()
        binding.bt.text = "movie"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.d("onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Timber.d("onStart")
        super.onStart()
    }

    override fun onResume() {
        Timber.d("onResume")
        super.onResume()
        searchViewModel.movieLoading.value?.let { showProgressDialogFragment(it) }
    }

    override fun onPause() {
        Timber.d("onPause")
        super.onPause()
    }

    override fun onStop() {
        Timber.d("onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Timber.d("onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Timber.d("onDetach")
        super.onDetach()
    }

    private fun initView() {
        searchViewModel.movieInfo.observe(viewLifecycleOwner) {
            Timber.d("movieInfo observe")
        }
        searchViewModel.movieLoading.observe(viewLifecycleOwner) {
            Timber.d("movieLoading observe : $it, isResumed : $isResumed")
            if (isResumed) showProgressDialogFragment(it)
        }
    }
}