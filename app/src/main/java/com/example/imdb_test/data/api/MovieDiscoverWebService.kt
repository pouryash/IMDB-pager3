package com.example.imdb_test.data.api

import com.example.imdb_test.data.models.DiscoverMovie
import com.example.imdb_test.utils.Const
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDiscoverWebService {

@GET("discover/movie?api_key=${Const.token}")
suspend fun getDiscoverMovie(
    @Query("page") page: Int,
) : DiscoverMovie

}