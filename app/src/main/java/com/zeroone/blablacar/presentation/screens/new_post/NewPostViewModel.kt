package com.zeroone.blablacar.presentation.screens.new_post

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroone.blablacar.domain.model.Post2
import com.zeroone.blablacar.domain.model.Response
import com.zeroone.blablacar.domain.model.google_map.direction.Route
import com.zeroone.blablacar.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    var newPostState = mutableStateOf(NewPostState())
        private set

    val isLoading = mutableStateOf(false)

    private val _eventFlow = MutableSharedFlow<NewPostUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun setFromLocation(locationState: LocationState) {
        newPostState.value = newPostState.value.copy(
            fromLocation = newPostState.value.fromLocation.copy(
                value = locationState.value,
                placeId = locationState.placeId,
                formatted_address = locationState.formatted_address
            )
        )
    }

    fun setFromLocationText(text: String) {
        newPostState.value = newPostState.value.copy(
            fromLocation = newPostState.value.fromLocation.copy(
                text = text,
                value = null,
                shortAddress = ""
            )
        )
    }

    fun setReferenceLocation(locationState: LocationState) {
        val waypoints = newPostState.value.waypoints
        waypoints[locationState.placeId]=locationState.value!!
        newPostState.value = newPostState.value.copy(
            referenceLocation = newPostState.value.referenceLocation.copy(
                value = locationState.value,
                placeId = locationState.placeId,
                formatted_address = locationState.formatted_address,
            ),
            waypoints = waypoints,
            currentRoute = null
        )
    }

    fun removeReferenceLocation(placeId: String) {
        val waypoints = newPostState.value.waypoints
        waypoints.remove(placeId)
        newPostState.value = newPostState.value.copy(waypoints = waypoints)
    }

    fun setReferenceLocationText(text: String) {
        newPostState.value = newPostState.value.copy(
            referenceLocation = newPostState.value.referenceLocation.copy(
                text = text,
                value = null,
                shortAddress = ""
            ),)
    }

    fun setToLocation(locationState: LocationState) {
        newPostState.value = newPostState.value.copy(
            toLocation = newPostState.value.toLocation.copy(
                value = locationState.value,
                placeId = locationState.placeId,
                formatted_address = locationState.formatted_address
            )
        )
    }

    fun setToLocationText(text: String) {
        newPostState.value = newPostState.value.copy(
            toLocation = newPostState.value.toLocation.copy(
                text = text,
                value = null,
                shortAddress = ""
            )
        )
    }

    fun setCurrentRoute(route: Route) {
        newPostState.value = newPostState.value.copy(currentRoute = route)
    }

    fun setDate(date: String) {
        newPostState.value = newPostState.value.copy(date = date)
    }

    fun setTime(time: String) {
        newPostState.value = newPostState.value.copy(time = time)
    }

    fun setPersonCount(value: Int) {
        var count = newPostState.value.personCount
        count += value
        if (count < 0) { count=0 }
        newPostState.value = newPostState.value.copy(personCount = count)
    }

    fun setPrice(value: Int) {
        var price = newPostState.value.price
        price += value
        if (price < 0) { price=0 }
        newPostState.value = newPostState.value.copy(price = price)
    }

    fun addPost(){

        val post = Post2(
            fromAddress = newPostState.value.fromLocation.text,
            toAddress = newPostState.value.toLocation.text,
            points = newPostState.value.currentRoute?.overview_polyline?.points,
            date = newPostState.value.date,
            time = newPostState.value.time,
            personCount = newPostState.value.personCount,
            price = newPostState.value.price.toFloat()
        )

        viewModelScope.launch {
            postRepository.newPost(post).collect{response->
                when(response){
                    is Response.Error -> {
                        isLoading.value= false
                        _eventFlow.emit(NewPostUiEvent.Error(response.message))
                    }
                    Response.Loading -> { isLoading.value = true }
                    is Response.Success -> {
                        isLoading.value = false
                        _eventFlow.emit(NewPostUiEvent.PostAdded)
                    }
                }
            }
        }
    }


    sealed class NewPostUiEvent{
        data class Error(val message: String) : NewPostUiEvent()
        object PostAdded: NewPostUiEvent()
    }
}