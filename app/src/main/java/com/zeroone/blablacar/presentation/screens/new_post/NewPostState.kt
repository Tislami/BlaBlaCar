package com.zeroone.blablacar.presentation.screens.new_post

import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.domain.model.google_map.direction.Route

data class NewPostState(

    val fromLocation: LocationState = LocationState(),
    val toLocation: LocationState = LocationState(),
    val referenceLocation: LocationState = LocationState(),
    val waypoints: MutableMap<String,LatLng> = mutableMapOf(),
    val currentRoute: Route? = null,
    val date: String="",
    val time: String="00:00",
    val personCount: Int=0,
    val price: Int=0,
)

data class LocationState(
    val text: String="",
    val value: LatLng?=null,
    val shortAddress: String="",
    val placeId: String="",
    val formatted_address: String="",
)

data class MapsLoadingState(
    val location: Boolean=false,
    val reverseLocation: Boolean=false,
    val autocomplete: Boolean=false,
    val direction: Boolean=false,
    val add: Boolean=false,
)


