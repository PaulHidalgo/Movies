package com.mojix.movie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mojix.movie.data.local.dao.MovieDao
import com.mojix.movie.data.local.entity.Movie

/**
 *Created by PHIDALGO on 2020
 */
@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao?

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null
        fun getDatabase(context: Context): MovieRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(MovieRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            Room.databaseBuilder(
                                context.applicationContext,
                                MovieRoomDatabase::class.java, "db_movies"
                            )
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}