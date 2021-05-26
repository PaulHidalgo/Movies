package com.mojix.movie.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mojix.movie.data.local.entity.Movie
import com.mojix.movie.repository.MovieDBRepository

/**
 *Created by PHIDALGO on 2020
 */
class MovieViewModel : ViewModel() {
    private var movieDbRepository: MovieDBRepository
    private var popularMovies: LiveData<List<Movie>>

    init {
        movieDbRepository = MovieDBRepository()
        popularMovies = movieDbRepository.popularMovies()!!
    }

    //Consultado desde la interfaz de usuario
    fun getPopularMovies(): LiveData<List<Movie>> {
        return popularMovies
    }
}