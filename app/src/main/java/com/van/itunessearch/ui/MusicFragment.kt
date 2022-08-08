package com.van.itunessearch.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.lifecycle.Lifecycle
import com.van.itunessearch.databinding.FragmentMusicBinding
import timber.log.Timber

class MusicFragment : TabFragment() {

    private var _binding: FragmentMusicBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentMusicBinding.inflate(inflater, container, false)
        // TODO move to TabFragment
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
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
        _binding = null
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
        }
    }
}