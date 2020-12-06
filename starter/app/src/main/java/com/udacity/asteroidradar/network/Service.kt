package com.udacity.asteroidradar.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface NetworkAsteroidService{
    @GET("CHANGE THIS")
    fun getAsteroidList() :Deferred<NetworkAsteroidContainer>
}

object Network {

}