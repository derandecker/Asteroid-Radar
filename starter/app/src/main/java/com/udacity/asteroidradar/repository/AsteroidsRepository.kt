package com.udacity.asteroidradar.repository

import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class AsteroidsRepository(private val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroids()) {
            it.asDomainModel()
        }

    suspend fun refreshAsteroids(
        startDate: String = todaysDate(),
        endDate: String,
        apiKey: String = BuildConfig.NASA_API_KEY
    ) {
        withContext(Dispatchers.IO) {
            val asteroidsString =
                Network.asteroids.getAsteroidList(startDate, endDate, apiKey).await()
            val asteroidsJson = JSONObject(asteroidsString)
            val asteroidList = parseAsteroidsJsonResult(asteroidsJson)
            database.asteroidDao.insertAll(*asteroidList.toTypedArray())
        }

    }

    private fun todaysDate(): String {
        val simpleDate = SimpleDateFormat("yyyy-dd-M")
        return simpleDate.format(Date())
    }
}