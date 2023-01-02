package com.zeroone.blablacar.domain.model.google_map.geocoding

data class AddressComponent(
    val long_name: String,
    val short_name: String,
    val types: List<String>
)