package net.vortex.atch.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.vortex.atch.BuildConfig
import net.vortex.atch.data.ApiResponse
import net.vortex.atch.util.hashGenerator
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// API credentials
private const val BASE_URL = "https://gateway.marvel.com/v1/public/"
private const val API_PRIV_KEY = BuildConfig.API_PRIV_KEY
private const val API_PUB_KEY = BuildConfig.API_PUB_KEY

// API limits
private const val API_LIMIT = 100
private const val API_OFFSET = 0

val api_data = hashGenerator(API_PRIV_KEY, API_PUB_KEY)
var time_stamp = api_data.first
var hash_code = api_data.second

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("characters")
    suspend fun getData(
        @Query("ts") ts: String = time_stamp,
        @Query("apikey") apikey: String = API_PUB_KEY,
        @Query("hash") hash: String = hash_code,
        @Query("limit") limit: Int = API_LIMIT,
        @Query("offset") offset: Int = API_OFFSET
    ):
            ApiResponse
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}