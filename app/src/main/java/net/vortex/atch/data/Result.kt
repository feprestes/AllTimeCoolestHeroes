package net.vortex.atch.data


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Result(
    val comics: Comics,
    @Json(name = "description")
    val description: String,
    val events: Events,
    @Json(name = "id")
    val id: Int,
    val modified: String,
    @Json(name = "name")
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail,
    val urls: List<Url>
) : Parcelable