package com.mojix.movie.data.remote

import com.mojix.movie.data.remote.models.ResponsePopularMovies
import retrofit2.Call
import retrofit2.http.GET

/**
 *Created by PHIDALGO on 2020
 */
interface MovieDBService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<ResponsePopularMovies>
}