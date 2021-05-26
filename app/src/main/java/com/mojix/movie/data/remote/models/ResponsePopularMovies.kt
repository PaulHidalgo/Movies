package com.mojix.movie.data.remote.models

import com.mojix.movie.data.local.entity.Movie

data class ResponsePopularMovies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)