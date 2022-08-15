package com.zeroone.blablacar.domain.model

data class Car(
    var name: String,
    var photoUrl: String?=null,
    var maxPerson: Int,
    var emptySeat: Int,
    var fullSeat: Int,
)
