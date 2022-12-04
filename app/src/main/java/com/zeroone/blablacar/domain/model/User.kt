package com.zeroone.blablacar.domain.model

import android.provider.ContactsContract.CommonDataKinds.Nickname

data class User(
    var name: String = "",
    var surname: String = "",
    var nickname: String = "",
    var photoUrl: String = "",
    var rates: List<Float> = listOf(),

    ) {

    fun getRate(): Float = rates.sum() / rates.size
    fun getRateSize(): Int = rates.size
}

val defaultUser = User(
    name = "Keanu",
    surname = "Reeves",
    nickname ="Keanu.Reeves",
    photoUrl = "https://firebasestorage.googleapis.com/v0/b/blablacar-5421d.appspot.com/o/keanu_reeves.jpg?alt=media&token=eb6e73aa-2be8-4407-8599-297d87c2a025",

)
