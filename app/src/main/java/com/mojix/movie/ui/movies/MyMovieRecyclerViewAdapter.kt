package com.mojix.movie.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mojix.movie.R
import com.mojix.movie.data.remote.ApiConstants
import com.mojix.movie.data.local.entity.Movie
import kotlinx.android.synthetic.main.fragment_movie_list.view.*


class MyMovieRecyclerViewAdapter() : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var movies: List<Movie> = ArrayList()

    init {
        mOnClickListener = View.OnClickListener { view ->
            val item = view.tag as Movie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.textViewTitle.text = item.title
        holder.imageViewTitle.load(ApiConstants.MOVIE_IMAGE_BASE + item.poster_path) {
            crossfade(true)
            placeholder(R.drawable.ic_movie)
            transformations(coil.transform.RoundedCornersTransformation())
        }

        with(holder.view) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = movies.size

    fun setData(popularMovies: List<Movie>?) {
        movies = popularMovies!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle: TextView = view.text_view_title
        val imageViewTitle = view.image_view_photo
    }
}