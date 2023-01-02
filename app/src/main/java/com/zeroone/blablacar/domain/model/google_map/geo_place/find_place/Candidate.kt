package com.zeroone.blablacar.domain.model.google_map.geo_place.find_place

data class Candidate(
    val formatted_address: String,
    val geometry: Geometry,
    val name: String,
    val opening_hours: OpeningHours,
    val rating: Double
)