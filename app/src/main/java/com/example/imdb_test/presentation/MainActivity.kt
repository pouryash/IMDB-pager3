package com.example.imdb_test.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import com.example.imdb_test.R
import com.example.imdb_test.data.RIM
import com.example.imdb_test.data.Status
import com.example.imdb_test.data.models.DiscoverMovie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    val discoverViewModel: DiscoverMovieViewModel by inject()

    private var discoveMovieJob: Job? = null

    private val adapter = DiscoverMovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        observe()
    }

    private fun initAdapter() {
        rv_main.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { adapter.retry() },
            footer = ReposLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    this,
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun observe() {
        discoveMovieJob?.cancel()
        discoveMovieJob = lifecycleScope.launch {
           val a = discoverViewModel.getDiscoverMovie()
            a.collectLatest {
                adapter.submitData(it)
            }
        }
    }

}