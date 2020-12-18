package com.udacity.asteroidradar.network
//
//import com.squareup.moshi.JsonClass
//import com.udacity.asteroidradar.domain.Asteroid
//import com.udacity.asteroidradar.database.DatabaseAsteroid
//
//
//data class NetworkAsteroidContainer(val asteroids: List<NetworkAsteroid>)
//
//data class NetworkAsteroid(
//    val id: Long, val codename: String, val closeApproachDate: String,
//    val absoluteMagnitude: Double, val estimatedDiameter: Double,
//    val relativeVelocity: Double, val distanceFromEarth: Double,
//    val isPotentiallyHazardous: Boolean)
//
//fun NetworkAsteroidContainer.asDomainModel(): List<Asteroid> {
//    return asteroids.map {
//        Asteroid(
//            id = it.id,
//            codename = it.codename,
//            closeApproachDate = it.closeApproachDate,
//            absoluteMagnitude = it.absoluteMagnitude,
//            estimatedDiameter = it.estimatedDiameter,
//            relativeVelocity = it.relativeVelocity,
//            distanceFromEarth = it.distanceFromEarth,
//            isPotentiallyHazardous = it.isPotentiallyHazardous)
//    }
//}
//
//fun NetworkAsteroidContainer.asDatabaseModel(): Array<DatabaseAsteroid> {
//    return asteroids.map {
//        DatabaseAsteroid(
//            id = it.id,
//            codename = it.codename,
//            closeApproachDate = it.closeApproachDate,
//            absoluteMagnitude = it.absoluteMagnitude,
//            estimatedDiameter = it.estimatedDiameter,
//            relativeVelocity = it.relativeVelocity,
//            distanceFromEarth = it.distanceFromEarth,
//            isPotentiallyHazardous = it.isPotentiallyHazardous)
//    }.toTypedArray()
//}