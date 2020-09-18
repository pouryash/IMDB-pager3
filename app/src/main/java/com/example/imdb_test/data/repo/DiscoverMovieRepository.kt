package com.example.imdb_test.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.imdb_test.data.ResultWrapper
import com.example.imdb_test.data.api.MovieDiscoverWebService
import com.example.imdb_test.data.models.DiscoverMovie
import com.example.imdb_test.data.repo.DiscoverMovie.DiscoverMoviePagingSource
import com.example.imdb_test.utils.Const.NETWORK_PAGE_SIZE
import com.example.imdb_test.utils.SafeApiCaller
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class DiscoverMovieRepository(
    private val api: MovieDiscoverWebService,
    private val dispatcher: CoroutineDispatcher,
    private val apiCaller: SafeApiCaller,
) {

    fun getDiscoverMovie(params: Map<String, Any?>): Flow<PagingData<DiscoverMovie.Movie>> {

        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false, initialLoadSize = NETWORK_PAGE_SIZE * 4 ),
            pagingSourceFactory = { DiscoverMoviePagingSource(api) }
        ).flow
    }

}