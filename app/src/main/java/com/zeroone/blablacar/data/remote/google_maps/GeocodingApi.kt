package com.zeroone.blablacar.data.remote.google_maps

import com.zeroone.blablacar.domain.model.google_map.geocoding.Geocoding
import com.zeroone.blablacar.domain.model.google_map.reverse_geocoding.ReverseGeocoding
import com.zeroone.blablacar.utils.GOOGLE_MAPS_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApi {
    @GET("geocode/json")
    suspend fun getLocation(
        @Query("place_id") placeId : String,
        @Query("key") key :String= GOOGLE_MAPS_API_KEY
    ) : Geocoding
}