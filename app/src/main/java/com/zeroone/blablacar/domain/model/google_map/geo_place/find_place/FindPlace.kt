package com.zeroone.blablacar.domain.model.google_map.geo_place.find_place

data class FindPlace(
    val candidates: List<Candidate>,
    val status: String
)