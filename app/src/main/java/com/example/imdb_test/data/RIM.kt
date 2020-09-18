package com.example.imdb_test.data

data class RIM<RequestData>(
    var state: Status? = null,
    var data: RequestData? = null,
    var error: String? = null,
    var code: Int? = null,
    var percent: Int? = 100
)

enum class Status { SUCCESSFUL, ERROR, LOADING, SUCCESSFUL_AUTH }