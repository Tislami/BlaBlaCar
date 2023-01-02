package com.zeroone.blablacar.presentation.screens.posts

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor() : ViewModel() {

    var newPostState = mutableStateOf(NewPostState())
        private set


    fun setFromLocation(value:String){
        newPostState.value = newPostState.value.copy(fromLocation = value)
    }

    fun setToLocation(value:String){
        newPostState.value = newPostState.value.copy(toLocation = value)
    }

    fun setFromLocationLatLng(latLng: LatLng){
        val value = "${latLng.latitude},${latLng.longitude}"
        newPostState.value = newPostState.value.copy(fromLocationLatLng = value)
    }

    fun setToLocationLatLng(latLng: LatLng){
        val value = "${latLng.latitude},${latLng.longitude}"
        newPostState.value = newPostState.value.copy(toLocationLatLng = value)
    }


}