package com.zeroone.blablacar.domain.model.google_map.direction

data class GeocodedWaypoint(
    val geocoder_status: String,
    val place_id: String,
    val types: List<String>
)