package com.zeroone.blablacar.presentation.screens.new_post

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.domain.model.Response
import com.zeroone.blablacar.domain.repository.google_maps.GoogleMapsApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor(
    private val googleMapsApiRepository: GoogleMapsApiRepository
) :
    ViewModel() {

    var newPostState = mutableStateOf(NewPostState())
        private set

    val isLoading = mutableStateOf(false)

    private val _eventFlow = MutableSharedFlow<GoogleMapsApiUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var autocompleteJob: Job? = null
    private fun autocomplete(input: String) {
        autocompleteJob?.cancel()
        autocompleteJob = viewModelScope.launch {
            googleMapsApiRepository.autocomplete(input = input).collect { response ->
                when (response) {
                    is Response.Error -> {
                        isLoading.value = false
                        _eventFlow.emit(GoogleMapsApiUiEvent.ShowSnackBar(response.message))
                    }
                    is Response.Loading -> {
                        isLoading.value = true
                    }
                    is Response.Success -> {
                        val suggestions: MutableMap<String, String> = mutableMapOf()
                        response.data.predictions.map {
                            suggestions[it.description] = it.place_id
                        }

                        newPostState.value = newPostState.value.copy(
                            suggestions = suggestions
                        )
                    }
                }
            }
        }
    }

    fun getDirection() {
        val destination = newPostState.value.toLocation
        val origin = newPostState.value.fromLocation

        if (destination != null && origin != null)
            viewModelScope.launch {
                googleMapsApiRepository.getDirection(
                    destination = "${destination.latitude},${destination.longitude}",
                    origin = "${origin.latitude},${origin.longitude}",
                ).collect { response ->
                    when (response) {
                        is Response.Error -> {
                            isLoading.value = false
                            _eventFlow.emit(GoogleMapsApiUiEvent.ShowSnackBar(response.message))
                        }
                        is Response.Loading -> {
                            isLoading.value = true
                        }
                        is Response.Success -> {
                            newPostState.value = newPostState.value.copy(
                                direction=response.data
                            )

                            Log.d("NewPostTag", "getDirection: ${response.data.routes.size}")
                            response.data.routes.forEach {
                                Log.d("NewPostTag", "getDirection: ${it.summary}")

                            }
                        }
                    }
                }
            }
    }

    fun getSelectedDirection(points : List<LatLng>) {
        /*val temp  = mutableListOf(points)
        newPostState.value.polyLinesPoints.map{
            if (it!=points){ temp.add(it) }
        }
        newPostState.value = newPostState.value.copy(polyLinesPoints = temp)
    */}

    fun getReverseLocation(latLng: LatLng) {
        val value = "${latLng.latitude},${latLng.longitude}"
        viewModelScope.launch {
            googleMapsApiRepository.getReverseLocation(value).collect { response ->
                when (response) {
                    is Response.Error -> {
                        isLoading.value = false
                        _eventFlow.emit(GoogleMapsApiUiEvent.ShowSnackBar(response.message))
                    }
                    is Response.Loading -> {
                        isLoading.value = true
                    }
                    is Response.Success -> {
                        val suggestions: MutableMap<String, String> = mutableMapOf()
                        response.data.results.map {
                            suggestions[it.formatted_address] = it.place_id
                        }

                        newPostState.value =
                            newPostState.value.copy(
                                reverseGeocoding = response.data,
                                suggestions = suggestions
                            )
                    }
                }
            }
        }
    }

    fun getLocation(placeId: String?, locationState: LocationState) {
        if (placeId != null)
            viewModelScope.launch {
                googleMapsApiRepository.getLocation(placeId).collect { response ->
                    when (response) {
                        is Response.Error -> {
                            isLoading.value = false
                            _eventFlow.emit(GoogleMapsApiUiEvent.ShowSnackBar(response.message))
                        }
                        is Response.Loading -> {
                            isLoading.value = true
                        }
                        is Response.Success -> {

                            var location = LatLng(40.40144780549906, 49.85737692564726)
                            response.data.results.map {
                                location =
                                    LatLng(it.geometry.location.lat, it.geometry.location.lng)
                            }
                            when (locationState) {
                                LocationState.From -> {
                                    newPostState.value =
                                        newPostState.value.copy(
                                            geocoding = response.data,
                                            fromLocation = location
                                        )
                                }
                                LocationState.To -> {
                                    newPostState.value =
                                        newPostState.value.copy(
                                            geocoding = response.data,
                                            toLocation = location
                                        )
                                }
                            }
                        }
                    }
                }
            }
    }

    fun setLocationTextValue(value:String, locationState: LocationState){
        when(locationState){
            LocationState.From -> {
                newPostState.value = newPostState.value.copy(
                    fromLocationText = value
                )
            }
            LocationState.To -> {
                newPostState.value = newPostState.value.copy(
                    toLocationText = value
                )
            }
        }
        autocomplete(value)
    }

    sealed class GoogleMapsApiUiEvent {
        data class ShowSnackBar(val message: String) : GoogleMapsApiUiEvent()
    }
}