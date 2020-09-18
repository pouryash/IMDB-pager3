package com.example.imdb_test.data.models

import com.google.gson.annotations.SerializedName

data class DiscoverMovie(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPage: Int,
    @SerializedName("total_results")
    val totalResult: Int,
    @SerializedName("results")
    val data: ArrayList<Movie>
){

    data class Movie(
        @SerializedName("poster_path")
        val posterPath: String? = null,
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("vote_count")
        val voteCount: String,
        @SerializedName("popularity")
        val popularity: String,
        @SerializedName("vote_average")
        val voteAverage: String,
    )

}