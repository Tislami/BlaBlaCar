package com.zeroone.blablacar.data.remote.google_maps.geo_place

import com.zeroone.blablacar.domain.model.google_map.geo_place.find_place.FindPlace
import com.zeroone.blablacar.utils.GOOGLE_MAPS_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface FindPlaceApi {
    @GET("place/findplacefromtext/json")
    suspend fun findPlaceFromText(
        @Query("input") input: String,
        @Query("inputtype") inputType: String="textquery",
        @Query("key") key :String= GOOGLE_MAPS_API_KEY
    ) : FindPlace
}