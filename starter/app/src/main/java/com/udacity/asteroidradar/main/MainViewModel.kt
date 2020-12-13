package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val asteroidsRepository = AsteroidsRepository(database)

//hard-coded strings for testing
    init {
        viewModelScope.launch {
            asteroidsRepository.refreshAsteroids(
                "2020-12-12",
                "2020-12-19",
                "z0mgCtuw7Ea23gTFFiels9UdLeOCSjLY0YHd5dq9"
            )
        }
    }

    val asteroids: LiveData<List<Asteroid>> = asteroidsRepository.asteroids

}