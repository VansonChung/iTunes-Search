package com.van.itunessearch.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.van.itunessearch.ui.adapter.MediaAdapter
import timber.log.Timber

class MusicFragment : TabFragment() {

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
        // use "fake loading view" to workaround ViewPager2 can't slid when loading(1/2)
//        searchViewModel.musicLoading.value?.let { showProgressDialogFragment(it) }
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
        searchViewModel.musicInfo.observe(viewLifecycleOwner) {
            Timber.d("musicInfo observe")
            submitAdapterData(it)
        }
        searchViewModel.musicStatus.observe(viewLifecycleOwner) {
            Timber.d("musicLoading observe : $it, isResumed : $isResumed")
            // use "fake loading view" to workaround ViewPager2 can't slid when loading(2/2)
//            if (isResumed) showProgressDialogFragment(it)
            apiStatusHandle(it)
        }
    }
}