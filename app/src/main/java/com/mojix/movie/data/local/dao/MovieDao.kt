package com.mojix.movie.data.local.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mojix.movie.data.local.entity.Movie

/**
 *Created by PHIDALGO on 2020
 */
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getPopularMovies(): MutableLiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movieList: MutableLiveData<List<Movie>>)

}