package com.zeroone.blablacar.presentation.screens.new_post

import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.domain.model.google_map.direction.Direction
import com.zeroone.blablacar.domain.model.google_map.geocoding.Geocoding
import com.zeroone.blablacar.domain.model.google_map.reverse_geocoding.ReverseGeocoding

data class NewPostState(
    val reverseGeocoding: ReverseGeocoding? = null,
    val geocoding: Geocoding? = null,
    val direction: Direction? = null,
    val suggestions: MutableMap<String,String> = mutableMapOf(),
    val fromLocation: LatLng? = null,
    val toLocation: LatLng? = null,
    val fromLocationText: String = "",
    val toLocationText: String = ""
)

data class NewPostLoadingState(
    val locationLoadingState: Boolean=false,
    val reverseLocationLoadingState: Boolean=false,
    val autocompleteLoadingState: Boolean=false,
    val directionLoadingState: Boolean=false,
)

enum class LocationState{
    From,
    To
}