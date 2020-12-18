package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.api.getNextSevenDaysFormattedDates
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject


class AsteroidsRepository(private val database: AsteroidDatabase) {

    // Replace "BuildConfig.NASA_API_KEY" below with your own API KEY
    private val nasaApiKey = BuildConfig.NASA_API_KEY

    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroids(getNextSevenDaysFormattedDates())) {
            it.asDomainModel()
        }

    private var _picOfTheDay = MutableLiveData<PictureOfDay>()
    val picOfTheDay: LiveData<PictureOfDay>
        get() = _picOfTheDay

    suspend fun refreshAsteroids(
        startDate: String,
        endDate: String,
        apiKey: String = nasaApiKey
    ) {
        withContext(Dispatchers.IO) {
            val asteroidsString =
                Network.asteroids.getAsteroidList(startDate, endDate, apiKey).await()
            val asteroidsJson = JSONObject(asteroidsString)
            val asteroidList = parseAsteroidsJsonResult(asteroidsJson)
            database.asteroidDao.insertAll(*asteroidList.toTypedArray())
        }

    }

    suspend fun refreshImageOfTheDay() {
        withContext(Dispatchers.Main) {
            _picOfTheDay.value =
                Network.imageOfTheDay.getImageOfTheDay(nasaApiKey)
                    .await()
        }
    }


}