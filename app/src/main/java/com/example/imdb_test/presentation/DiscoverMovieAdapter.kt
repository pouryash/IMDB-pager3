package com.example.imdb_test.presentation

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb_test.data.models.DiscoverMovie

class DiscoverMovieAdapter : PagingDataAdapter<DiscoverMovie.Movie, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DiscoverMovieViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as DiscoverMovieViewHolder).bind(repoItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<DiscoverMovie.Movie>() {
            override fun areItemsTheSame(oldItem: DiscoverMovie.Movie, newItem: DiscoverMovie.Movie): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: DiscoverMovie.Movie, newItem: DiscoverMovie.Movie): Boolean =
                oldItem == newItem
        }
    }
}