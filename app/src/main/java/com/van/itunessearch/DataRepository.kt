package com.van.itunessearch

import com.van.itunessearch.apis.ITunesApi

object DataRepository {

    suspend fun searchMusic(input: String) =
        ITunesApi.retrofitService.searchMusic(input)

    suspend fun searchMovie(input: String) =
        ITunesApi.retrofitService.searchMovie(input)
}