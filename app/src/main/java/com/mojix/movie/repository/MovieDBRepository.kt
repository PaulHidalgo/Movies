package com.mojix.movie.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.mojix.movie.util.common.BaseApp
import com.mojix.movie.data.local.MovieRoomDatabase
import com.mojix.movie.data.local.dao.MovieDao
import com.mojix.movie.data.local.entity.Movie
import com.mojix.movie.data.remote.MovieDBClient
import com.mojix.movie.data.remote.MovieDBService
import com.mojix.movie.data.remote.models.ResponsePopularMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *Created by PHIDALGO on 2020
 */
class MovieDBRepository {
    var movieDBService: MovieDBService? = null
    var movieDBClient: MovieDBClient? = null
    var popularMovies: MutableLiveData<List<Movie>>? = null
    var movieRoomDatabase: MovieRoomDatabase? = null
    var movieDao: MovieDao? = null

    init {
        movieDBClient = MovieDBClient.instance
        //Datos remotos - Retrofit
        movieDBService = movieDBClient?.getMovieDBService()

        //Datos locales - Room
//        movieRoomDatabase = movieDBClient?.getMovieDBLocal()
//        movieDao = movieRoomDatabase?.movieDao()


        //Populate Movies
        popularMovies = popularMovies()
    }
//Tipo que devuelve room(Database local), Tipo que devuelve la API con Retrofit

    fun popularMovies(): MutableLiveData<List<Movie>>? {
        if (popularMovies == null) {
            popularMovies = MutableLiveData<List<Movie>>()
        }
        val call: Call<ResponsePopularMovies>? = movieDBService?.getPopularMovies()
        call?.enqueue(object : Callback<ResponsePopularMovies> {
            override fun onFailure(call: Call<ResponsePopularMovies>, t: Throwable) {
                Toast.makeText(BaseApp.instance, "Error en la llamada", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ResponsePopularMovies>,
                response: Response<ResponsePopularMovies>
            ) {
                if (response.isSuccessful) {
                    popularMovies?.value = response.body()?.results
                }
            }

        })
        return popularMovies
    }

}