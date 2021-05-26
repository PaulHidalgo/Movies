package com.mojix.movie.data.remote

import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.mojix.movie.data.remote.ApiConstants.Companion.MOVIE_API_KEY
import com.mojix.movie.data.remote.ApiConstants.Companion.PARAM_API_KEY
import com.mojix.movie.data.remote.ApiConstants.Companion.PARAM_LANGUAGE
import com.mojix.movie.util.common.BaseApp
import com.mojix.movie.util.common.Constants
import okhttp3.Interceptor
import okhttp3.Response

/**
 *Created by PHIDALGO on 2020
 */
class MovieInterceptor : Interceptor {
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var language: String

    override fun intercept(chain: Interceptor.Chain): Response {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseApp.instance)
        val lang = sharedPreferences.getString(Constants.SELECTED_LANGUAGE, "en")
        when (lang) {
            Constants.ENGLISH -> language = "en-EN"
            Constants.SPANISH -> language = "es-ES"
        }


        val urlWithParameters = chain.request()
            .url.newBuilder()
            .addQueryParameter(PARAM_API_KEY, MOVIE_API_KEY)
            .addQueryParameter(PARAM_LANGUAGE, language)
            .build()

        var request = chain.request()

        request = request?.newBuilder()
            .url(urlWithParameters)
            .addHeader("Conten-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()

        Log.i("Request::", urlWithParameters.toString())

        return chain.proceed(request)
    }
}