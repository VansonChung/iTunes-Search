package com.van.itunessearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.van.itunessearch.DataRepository
import com.van.itunessearch.apis.resp.MovieInfo
import com.van.itunessearch.apis.resp.MusicInfo
import com.van.itunessearch.safeLaunch
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import timber.log.Timber

class SearchViewModel(private val repository: DataRepository) : ViewModel() {

    // Music --------------------------------------------
    private var _musicInfo = MutableLiveData<List<MusicInfo>>()
    val musicInfo: LiveData<List<MusicInfo>> get() = _musicInfo

    private var _musicLoading = MutableLiveData<Boolean>()
    val musicLoading: LiveData<Boolean> get() = _musicLoading
    // Music End ----------------------------------------

    // Movie --------------------------------------------
    private var _movieInfo = MutableLiveData<List<MovieInfo>>()
    val movieInfo: LiveData<List<MovieInfo>> get() = _movieInfo

    private var _movieLoading = MutableLiveData<Boolean>()
    val movieLoading: LiveData<Boolean> get() = _movieLoading
    // Movie End ----------------------------------------

    private var jobMusic: Job? = null
    private var jobMovie: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e("Exception handled: ${throwable.localizedMessage}")
        // dismiss pb and show error msg
    }

    fun searchMusic(input: String) {
        Timber.d("searchMusic")
        jobMusic?.cancel()
        jobMusic = viewModelScope.safeLaunch(exceptionHandler) {
            withContext(Dispatchers.Main) {
                _musicLoading.value = true
            }
            withContext(Dispatchers.IO) {
                val resp = repository.searchMusic(input)
                if (resp.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _musicInfo.value = resp.body()?.results
                        _musicLoading.value = false
                    }
                }
            }

        }
    }

    fun searchMovie(input: String) {
        Timber.d("searchMovie")
        jobMovie?.cancel()
        jobMovie = viewModelScope.safeLaunch(exceptionHandler) {
            withContext(Dispatchers.Main) {
                _movieLoading.value = true
            }
            withContext(Dispatchers.IO) {
                val resp = repository.searchMovie(input)
                if (resp.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _movieInfo.value = resp.body()?.results
                        _movieLoading.value = false
                    }
                }
            }
        }
    }

    class Factory(private val repository: DataRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchViewModel(repository) as T
            }
            throw IllegalArgumentException("Unable to construct viewModel")
        }
    }
}