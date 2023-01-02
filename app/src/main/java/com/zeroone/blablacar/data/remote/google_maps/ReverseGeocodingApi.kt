package com.zeroone.blablacar.data.remote.google_maps

import com.zeroone.blablacar.domain.model.google_map.reverse_geocoding.ReverseGeocoding
import com.zeroone.blablacar.utils.GOOGLE_MAPS_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ReverseGeocodingApi {
    @GET("geocode/json")
    suspend fun getLocation(
        @Query("latlng") latLng : String,
        @Query("language") language : String="az",
        @Query("key") key :String= GOOGLE_MAPS_API_KEY
    ) : ReverseGeocoding
}