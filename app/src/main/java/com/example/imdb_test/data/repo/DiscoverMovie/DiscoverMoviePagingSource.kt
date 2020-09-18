package com.example.imdb_test.data.repo.DiscoverMovie

import androidx.paging.PagingSource
import com.example.imdb_test.data.api.MovieDiscoverWebService
import com.example.imdb_test.data.models.DiscoverMovie
import com.example.imdb_test.utils.Const
import retrofit2.HttpException
import java.io.IOException



class DiscoverMoviePagingSource(private val service: MovieDiscoverWebService) :
    PagingSource<Int, DiscoverMovie.Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMovie.Movie> {
        val position = params.key ?: Const.PAGE_INDEX
        return try {
            val response = service.getDiscoverMovie(position)
            val repos = response.data
            LoadResult.Page(
                data = repos,
                prevKey = if (position == Const.PAGE_INDEX) null else position - 1,
                nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}