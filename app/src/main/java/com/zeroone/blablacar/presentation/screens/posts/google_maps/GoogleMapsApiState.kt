package com.zeroone.blablacar.presentation.screens.posts.google_maps

import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.data.remote.google_maps.GeocodingApi
import com.zeroone.blablacar.domain.model.google_map.direction.Direction
import com.zeroone.blablacar.domain.model.google_map.geo_place.autocomplete.Autocomplete
import com.zeroone.blablacar.domain.model.google_map.geo_place.autocomplete.Prediction
import com.zeroone.blablacar.domain.model.google_map.geo_place.find_place.FindPlace
import com.zeroone.blablacar.domain.model.google_map.geocoding.Geocoding
import com.zeroone.blablacar.domain.model.google_map.reverse_geocoding.ReverseGeocoding

data class GoogleMapsApiState(
    val autocomplete: Autocomplete? = null,
    val findPlace: FindPlace? = null,
    val reverseGeocoding: ReverseGeocoding? = null,
    val geocoding: Geocoding? = null,
    val direction: Direction? = null,
    val polyLinesPoints: List<LatLng> = emptyList(),
    val suggestions : MutableMap<String,String> = mutableMapOf(),
    val fromLocation: LatLng? = null,
    val toLocation: LatLng? = null
)