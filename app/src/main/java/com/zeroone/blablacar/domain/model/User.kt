package com.zeroone.blablacar.domain.model

data class User(
    var name: String,
    var surname: String,
    var rates: List<Float>,
    ){

    fun getRate() : Float = rates.sum()/rates.size
    fun getRateSize() : Int = rates.size
}
