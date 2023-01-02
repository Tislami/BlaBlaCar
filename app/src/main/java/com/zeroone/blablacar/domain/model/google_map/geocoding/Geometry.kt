package com.zeroone.blablacar.domain.model.google_map.geocoding

data class Geometry(
    val bounds: Bounds,
    val location: Location,
    val location_type: String,
    val viewport: Viewport
)