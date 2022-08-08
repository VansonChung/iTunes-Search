package com.van.itunessearch

import com.van.itunessearch.apis.RetrofitService

object DataRepository {

    suspend fun searchMusic(input: String) =
        RetrofitService.iTunesSearchApi.searchMusic(input)

    suspend fun searchMovie(input: String) =
        RetrofitService.iTunesSearchApi.searchMovie(input)
}