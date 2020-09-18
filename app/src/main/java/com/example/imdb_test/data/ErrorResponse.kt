package com.example.imdb_test.data

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("message")
    val message: String? = ""
)