package com.zeroone.blablacar.presentation.screens.posts

import com.google.android.gms.maps.model.LatLng

data class NewPostState(
    val fromLocation: String= "",
    val toLocation: String="",
    val fromLocationLatLng: String = "",
    val toLocationLatLng: String= "",
    val directionLocations: List<LatLng> = emptyList(),
)


