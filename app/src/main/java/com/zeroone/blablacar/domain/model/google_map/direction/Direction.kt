package com.zeroone.blablacar.domain.model.google_map.direction

data class Direction(
    val geocoded_waypoints: List<GeocodedWaypoint>,
    val routes: List<Route>,
    val status: String
)