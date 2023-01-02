package com.zeroone.blablacar.data.remote.google_maps.geo_place

import com.zeroone.blablacar.domain.model.google_map.geo_place.autocomplete.Autocomplete
import com.zeroone.blablacar.utils.GOOGLE_MAPS_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface AutocompleteApi {
    @GET("place/autocomplete/json")
    suspend fun getPlaceAutocomplete(
        @Query("input") input: String,
        @Query("components") components: String="country:az",
        @Query("key") key :String= GOOGLE_MAPS_API_KEY
    ) : Autocomplete
}