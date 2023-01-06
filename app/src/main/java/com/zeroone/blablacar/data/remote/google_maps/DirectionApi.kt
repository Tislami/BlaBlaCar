package com.zeroone.blablacar.data.remote.google_maps

import com.zeroone.blablacar.domain.model.google_map.direction.Direction
import com.zeroone.blablacar.utils.GOOGLE_MAPS_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionApi {
    @GET("directions/json")
    suspend fun getDirection(
        @Query("destination") destination: String,
        @Query("origin") origin: String,
        @Query("waypoints") waypoints: String,
        @Query("alternatives") alternatives: Boolean = true,
        @Query("mode") mode: String = "DRIVING",
        @Query("region") region: String="AZ",
        @Query("key") key :String= GOOGLE_MAPS_API_KEY
    ) : Direction
}