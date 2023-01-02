package com.zeroone.blablacar.domain.model.google_map.geo_place.autocomplete

data class StructuredFormatting(
    val main_text: String,
    val main_text_matched_substrings: List<MainTextMatchedSubstring>,
    val secondary_text: String
)