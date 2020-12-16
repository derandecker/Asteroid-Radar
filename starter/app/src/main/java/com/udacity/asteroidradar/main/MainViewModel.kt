package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.getEndDate
import com.udacity.asteroidradar.api.getTodaysDate
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val asteroidsRepository = AsteroidsRepository(database)


    //getTodaysDate() and getEndDate() from NetworkUtils.kt in api package -- see imports
    init {
        viewModelScope.launch {
            try{
                asteroidsRepository.refreshImageOfTheDay()
            } catch (e: Exception){
                Log.e("VIEWMODELSCOPE", "Error refreshing Image Of The Day")
            }

            try {
                asteroidsRepository.refreshAsteroids(getTodaysDate(), getEndDate())
            } catch (e: Exception) {
                Log.e("VIEWMODELSCOPE", "Error refreshing asteroids")
            }

        }
    }

    val photoOfTheDay: LiveData<PictureOfDay> = asteroidsRepository.picOfTheDay

    val asteroids: LiveData<List<Asteroid>> = asteroidsRepository.asteroids

}