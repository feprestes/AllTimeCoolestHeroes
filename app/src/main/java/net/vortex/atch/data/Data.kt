package net.vortex.atch.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    @Json(name = "results")
    val results: List<Result>,
    val total: Int
)