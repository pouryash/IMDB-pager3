package com.example.imdb_test.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb_test.R
import com.example.imdb_test.data.models.DiscoverMovie
import kotlinx.android.synthetic.main.movie_item.view.*

class DiscoverMovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
    fun bind(movie: DiscoverMovie.Movie?) {
        if (movie != null) {
            itemView.tv_movie_name.text = movie.originalTitle
            itemView.tv_movie_date_lang.text = "$movie.releaseDate (${movie.originalLanguage})"
            itemView.tv_movie_overview.text = movie.overview

        }
    }

    companion object {
        fun create(parent: ViewGroup): DiscoverMovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false)
            return DiscoverMovieViewHolder(view)
        }
    }
}

