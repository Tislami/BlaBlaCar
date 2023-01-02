package com.zeroone.blablacar.domain.model.google_map.reverse_geocoding

data class ReverseGeocoding(
    val plus_code: PlusCode,
    val results: List<Result>,
    val status: String
)