package com.zeroone.blablacar.domain.model

import android.provider.ContactsContract.CommonDataKinds.Nickname
import androidx.compose.ui.res.stringResource
import com.zeroone.blablacar.R

data class User(
    var name: String = "",
    var surname: String = "",
    var about: String = "",
    var photoUrl: String = "",
    var rate: String = "",


    )

val defaultUser = User(
    name = "Keanu",
    surname = "Reeves",
    about = "",
    photoUrl = "https://firebasestorage.googleapis.com/v0/b/blablacar-5421d.appspot.com/o/keanu_reeves.jpg?alt=media&token=eb6e73aa-2be8-4407-8599-297d87c2a025",
    rate = "Yeni Başlayan",

)
