package com.van.itunessearch.apis

import com.van.itunessearch.BuildConfig
import com.van.itunessearch.apis.resp.MovieSearchResp
import com.van.itunessearch.apis.resp.MusicSearchResp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://itunes.apple.com/"

private const val SEARCH_MUSIC = "/search?media=music"
private const val SEARCH_MOVIE = "/search?media=movie"

private const val OKHTTP_TIMEOUT = 15L

private val okhttpClient =
    OkHttpClient().newBuilder()
        .connectTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS)
        .also {
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                it.addInterceptor(logging)
            }
        }.build()

private val retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient)
        .build()

interface ITunesApiService {

    // https://itunes.apple.com/search?media=music&term=michael
    @GET(SEARCH_MUSIC)
    suspend fun searchMusic(
        @Query(value = "term", encoded = true) input: String
    ): Response<MusicSearchResp>

    // https://itunes.apple.com/search?media=movie&term=michael
    @GET(SEARCH_MOVIE)
    suspend fun searchMovie(
        @Query(value = "term", encoded = true) input: String
    ): Response<MovieSearchResp>

}

object ITunesApi {
    val retrofitService: ITunesApiService by lazy { retrofit.create(ITunesApiService::class.java) }
}