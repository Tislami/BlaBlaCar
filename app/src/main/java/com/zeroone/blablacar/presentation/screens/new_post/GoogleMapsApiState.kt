package com.zeroone.blablacar.presentation.screens.new_post

import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.domain.model.google_map.direction.Direction
import com.zeroone.blablacar.domain.model.google_map.geocoding.Geocoding
import com.zeroone.blablacar.domain.model.google_map.reverse_geocoding.ReverseGeocoding

data class GoogleMapsApiState(
    val reverseGeocoding: ReverseGeocoding? = null,
    val geocoding: Geocoding? = null,
    val direction: Direction? = null,
    val waypoints: MutableMap<String, LatLng?> = mutableMapOf(),
    val suggestions: MutableMap<String,String> = mutableMapOf(),
    val currentLocation: LocationState = LocationState(),
)