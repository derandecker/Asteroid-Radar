package com.udacity.asteroidradar.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.udacity.asteroidradar.Constants
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkAsteroidService {
    @GET("neo/rest/v1/feed")
    fun getAsteroidList(
        @Query("start_date") startDate : String = "2020-12-09",
        @Query("end_date") endDate: String = "2020-12-16",
        @Query("api_key") apiKey: String = "z0mgCtuw7Ea23gTFFiels9UdLeOCSjLY0YHd5dq9"
    ): Deferred<String>
}

object Network {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val asteroids = retrofit.create(NetworkAsteroidService::class.java)
}
