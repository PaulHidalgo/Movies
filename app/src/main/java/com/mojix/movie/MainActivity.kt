package com.mojix.movie

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mojix.movie.util.common.Constants.Companion.ENGLISH
import com.mojix.movie.util.common.Constants.Companion.SELECTED_LANGUAGE
import com.mojix.movie.util.common.Constants.Companion.SPANISH
import com.mojix.movie.util.helper.ApplicationLanguageHelper

class MainActivity : AppCompatActivity() {
    var booleanLanguage: Boolean = false
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_movies
            )
        )
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        findViewById<FloatingActionButton>(R.id.language).setOnClickListener { view ->
            if (booleanLanguage) {
                changeApplicationLanguage(SPANISH)
                !booleanLanguage
            } else {
                changeApplicationLanguage(ENGLISH)
            }

        }

    }

    override fun attachBaseContext(newBase: Context?) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(newBase)
        val lang = sharedPreferences.getString(SELECTED_LANGUAGE, "en")
        super.attachBaseContext(ApplicationLanguageHelper.wrap(newBase!!, lang!!))
    }

    private fun changeApplicationLanguage(language: String) {
        val sharedPreferencesEditor = sharedPreferences.edit()
        when (language) {
            ENGLISH -> sharedPreferencesEditor?.putString(SELECTED_LANGUAGE, ENGLISH)
            SPANISH -> sharedPreferencesEditor?.putString(SELECTED_LANGUAGE, SPANISH)
        }
        sharedPreferencesEditor?.apply()
        recreate()
    }
}