package com.udacity.asteroidradar

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(val id: Long, val codename: String, val closeApproachDate: String,
                    val absoluteMagnitude: Double = 0.0, val estimatedDiameter: Double = 0.0,
                    val relativeVelocity: Double = 0.0, val distanceFromEarth: Double = 0.0,
                    val isPotentiallyHazardous: Boolean) : Parcelable