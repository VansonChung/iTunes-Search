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

object RetrofitService {

    // by lazy is better ?!
    private val okhttpClient by lazy {
        OkHttpClient()
            .newBuilder()
            .connectTimeout(ApiConfig.OKHTTP_TIMEOUT, TimeUnit.SECONDS).also {
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.level = HttpLoggingInterceptor.Level.BODY
                    it.addInterceptor(logging)
                }
            }
            .build()
    }

    // by lazy is better ?!
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient)
            .build()
    }

//    private val okhttpClient =
//        OkHttpClient().newBuilder()
//            .connectTimeout(ApiConfig.OKHTTP_TIMEOUT, TimeUnit.SECONDS)
//            .also {
//                if (BuildConfig.DEBUG) {
//                    val logging = HttpLoggingInterceptor()
//                    logging.level = HttpLoggingInterceptor.Level.BODY
//                    it.addInterceptor(logging)
//                }
//            }.build()

//    private fun retrofit() =
//        Retrofit.Builder()
//            .baseUrl(ApiConfig.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okhttpClient)
//            .build()

    val iTunesSearchApi: ITunesSearchApi by lazy {
        retrofit.create(ITunesSearchApi::class.java)
    }
}

interface ITunesSearchApi {

    @GET(ApiConfig.SEARCH_MUSIC)
    suspend fun searchMusicByTerm(
        @Query(value = "term", encoded = true) term: String
    ): Response<MusicSearchResp>

    @GET(ApiConfig.SEARCH_MOVIE)
    suspend fun searchMovieByTerm(
        @Query(value = "term", encoded = true) term: String
    ): Response<MovieSearchResp>

}