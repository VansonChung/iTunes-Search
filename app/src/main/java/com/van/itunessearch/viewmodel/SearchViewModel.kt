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

sealed class ApiStatus {
    object Loading : ApiStatus()
    class Error(var msg: String) : ApiStatus()
    object Done : ApiStatus()
}

class SearchViewModel(private val repository: DataRepository) : ViewModel() {

    // Music --------------------------------------------
    private var _musicInfo = MutableLiveData<List<MediaInfo>>()
    val musicInfo: LiveData<List<MediaInfo>> get() = _musicInfo

    private var _musicStatus = MutableLiveData<ApiStatus>()
    val musicStatus: LiveData<ApiStatus> get() = _musicStatus
    // Music End ----------------------------------------

    // Movie --------------------------------------------
    private var _movieInfo = MutableLiveData<List<MediaInfo>>()
    val movieInfo: LiveData<List<MediaInfo>> get() = _movieInfo

    private var _movieStatus = MutableLiveData<ApiStatus>()
    val movieStatus: LiveData<ApiStatus> get() = _movieStatus
    // Movie End ----------------------------------------

    private var jobMusic: Job? = null
    private var jobMovie: Job? = null

    private val musicExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e("Music Exception handler : ${throwable.localizedMessage}")
        _musicStatus.value =
            ApiStatus.Error("Music Exception : ${throwable.localizedMessage}")
    }

    private val movieExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e("Movie Exception handler : ${throwable.localizedMessage}")
        _movieStatus.value =
            ApiStatus.Error("Music Exception : ${throwable.localizedMessage}")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
        jobMusic?.cancel()
        jobMovie?.cancel()
    }

    fun searchMusic(input: String) {
        Timber.d("searchMusic")
        jobMusic?.cancel()
        jobMusic = viewModelScope.safeLaunch(musicExceptionHandler) {
            withContext(Dispatchers.Main) {
                _musicStatus.value = ApiStatus.Loading
            }
            withContext(Dispatchers.IO) {
                val resp = repository.searchMusic(input)
                if (resp.isSuccessful) {
                    val info = transferMusic(resp.body()?.results)
                    withContext(Dispatchers.Main) {
                        _musicInfo.value = info
                        _musicStatus.value = ApiStatus.Done
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _musicStatus.value = ApiStatus.Error("Error : ${resp.message()}")
                    }
                }
            }
        }
    }

    fun searchMovie(input: String) {
        Timber.d("searchMovie")
        jobMovie?.cancel()
        jobMovie = viewModelScope.safeLaunch(movieExceptionHandler) {
            withContext(Dispatchers.Main) {
                _movieStatus.value = ApiStatus.Loading
            }
            withContext(Dispatchers.IO) {
                val resp = repository.searchMovie(input)
                if (resp.isSuccessful) {
                    val info = transferMovie(resp.body()?.results)
                    withContext(Dispatchers.Main) {
                        _movieInfo.value = info
                        _movieStatus.value = ApiStatus.Done
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _movieStatus.value = ApiStatus.Error("Error : ${resp.message()}")
                    }
                }
            }
        }
    }

    private fun transferMusic(musicInfos: List<MusicInfo>?): List<MediaInfo> {
        val result = ArrayList<MediaInfo>()
        musicInfos?.forEach {
            val imgUrl: String = when {
                it.artworkUrl100.isNotEmpty() -> it.artworkUrl100
                it.artworkUrl60.isNotEmpty() -> it.artworkUrl60
                it.artworkUrl30.isNotEmpty() -> it.artworkUrl30
                else -> ""
            }
            result.add(MediaInfo(imgUrl, it.artistName, it.trackName))
        }
        return result
    }

    private fun transferMovie(movieInfos: List<MovieInfo>?): List<MediaInfo> {
        val result = ArrayList<MediaInfo>()
        movieInfos?.forEach {
            val imgUrl: String = when {
                it.artworkUrl100.isNotEmpty() -> it.artworkUrl100
                it.artworkUrl60.isNotEmpty() -> it.artworkUrl60
                it.artworkUrl30.isNotEmpty() -> it.artworkUrl30
                else -> ""
            }
            result.add(MediaInfo(imgUrl, it.artistName, it.trackName))
        }
        return result
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