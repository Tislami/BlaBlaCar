package com.zeroone.blablacar.domain.model.google_map.geo_place.autocomplete

data class Autocomplete(
    val predictions: List<Prediction>,
    val status: String
)