package com.zeroone.blablacar.domain.model.google_map.geocoding

data class Geocoding(
    val results: List<Result>,
    val status: String
)