package com.van.itunessearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.van.itunessearch.DataRepository
import com.van.itunessearch.apis.resp.MovieInfo
import com.van.itunessearch.apis.resp.MusicInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: DataRepository) : ViewModel() {

    private var _musicInfo = MutableLiveData<List<MusicInfo>>()
    val musicInfo: LiveData<List<MusicInfo>> get() = _musicInfo

    private var _movieInfo = MutableLiveData<List<MovieInfo>>()
    val movieInfo: LiveData<List<MovieInfo>> get() = _movieInfo

    fun searchMusic(input: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = repository.searchMusic(input)
            if (resp.isSuccessful) {
                _musicInfo.postValue(resp.body()?.results)
            }
        }
    }

    fun searchMovie(input: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = repository.searchMovie(input)
            if (resp.isSuccessful) {
                _movieInfo.postValue(resp.body()?.results)
            }
        }
    }
}