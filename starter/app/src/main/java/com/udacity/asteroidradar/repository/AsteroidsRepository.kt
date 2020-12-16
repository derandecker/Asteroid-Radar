package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject


class AsteroidsRepository(private val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroids()) {
            it.asDomainModel()
        }

    private var _picOfTheDay = MutableLiveData<PictureOfDay>()
    val picOfTheDay: LiveData<PictureOfDay>
        get() = _picOfTheDay

    suspend fun refreshAsteroids(
        startDate: String,
        endDate: String,
        apiKey: String = BuildConfig.NASA_API_KEY   // insert your API KEY here
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
                Network.imageOfTheDay.getImageOfTheDay(BuildConfig.NASA_API_KEY)
                    .await()
        }
    }


}