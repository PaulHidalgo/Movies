package com.mojix.movie.data.remote

import com.mojix.movie.data.remote.ApiConstants.Companion.MOVIE_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *Created by PHIDALGO on 2020
 */
class MovieDBClient {

    private val movieDBService: MovieDBService
//    private val movieRoomDatabase: MovieRoomDatabase
    private val retrofit: Retrofit

    companion object {
        var instance: MovieDBClient? = null
            get() {
                if (field == null) {
                    instance =
                        MovieDBClient()
                }
                return field
            }
    }

    init {
        //Request Interceptor
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(MovieInterceptor())

        //Remote - Retrofit
        val client = okHttpClientBuilder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(MOVIE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        movieDBService = retrofit.create(MovieDBService::class.java)

        //Datos locales - Room
//        movieRoomDatabase = MovieRoomDatabase.getDatabase(BaseApp.instance)!!
    }

    fun getMovieDBService() = movieDBService

//    fun getMovieDBLocal() = movieRoomDatabase
}