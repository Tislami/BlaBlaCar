package com.zeroone.blablacar.presentation.screens.new_post

import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.domain.model.google_map.direction.Direction
import com.zeroone.blablacar.domain.model.google_map.direction.GeocodedWaypoint
import com.zeroone.blablacar.domain.model.google_map.direction.Route
import com.zeroone.blablacar.domain.model.google_map.geocoding.Geocoding
import com.zeroone.blablacar.domain.model.google_map.reverse_geocoding.ReverseGeocoding

data class NewPostState(
    val reverseGeocoding: ReverseGeocoding? = null,
    val geocoding: Geocoding? = null,
    val direction: Direction? = null,
    val currentRoute: Route? = null,
    val waypoints: MutableMap<String,LatLng?> = mutableMapOf(),
    val suggestions: MutableMap<String,String> = mutableMapOf(),
    val fromLocation: LatLng? = null,
    val toLocation: LatLng? = null,
    val newLocation: LatLng? = null,
    val fromLocationText: String = "",
    val toLocationText: String = "",
    val newLocationText: String = "",
    val newLocationPlaceId: String = "",
)

data class NewPostLoadingState(
    val locationLoadingState: Boolean=false,
    val reverseLocationLoadingState: Boolean=false,
    val autocompleteLoadingState: Boolean=false,
    val directionLoadingState: Boolean=true,
)

enum class LocationState{
    From,
    To,
    NewLocation
}