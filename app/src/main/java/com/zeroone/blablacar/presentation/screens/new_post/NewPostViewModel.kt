package com.zeroone.blablacar.presentation.screens.new_post

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.domain.model.Response
import com.zeroone.blablacar.domain.model.google_map.direction.Route
import com.zeroone.blablacar.domain.repository.google_maps.GoogleMapsApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


//Sheki : ChIJOavewsaHR0ARTJ8mOt92cKU
//Agdam : ChIJux8yguSRPkARBxWipqpZS4I

@HiltViewModel
class NewPostViewModel @Inject constructor(
    private val googleMapsApiRepository: GoogleMapsApiRepository
) :
    ViewModel() {

    var newPostState = mutableStateOf(NewPostState())
        private set

    val newPostLoadingState = mutableStateOf(NewPostLoadingState())

    private val _eventFlow = MutableSharedFlow<NewPostUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun setLocationTextValue(value: String, locationState: LocationState) {
        when (locationState) {
            LocationState.From -> {
                newPostState.value = newPostState.value.copy(
                    fromLocationText = value,
                    fromLocation = null
                )
            }
            LocationState.To -> {
                newPostState.value = newPostState.value.copy(
                    toLocationText = value,
                    toLocation = null
                )
            }
            LocationState.NewLocation -> {
                newPostState.value = newPostState.value.copy(
                    newLocationText = value,
                    newLocation = null
                )
            }
        }
        autocomplete(value)
    }

    private var autocompleteJob: Job? = null
    private fun autocomplete(input: String) {
        autocompleteJob?.cancel()
        autocompleteJob = viewModelScope.launch {
            googleMapsApiRepository.autocomplete(input = input).collect { response ->
                when (response) {
                    is Response.Error -> {
                        newPostLoadingState.value =
                            newPostLoadingState.value.copy(autocompleteLoadingState = false)
                        _eventFlow.emit(NewPostUiEvent.ShowSnackBar(response.message))
                    }
                    is Response.Loading -> {
                        newPostLoadingState.value =
                            newPostLoadingState.value.copy(autocompleteLoadingState = true)
                    }
                    is Response.Success -> {

                        val suggestions: MutableMap<String, String> = mutableMapOf()
                        response.data.predictions.map {
                            suggestions[it.description] = it.place_id
                        }

                        newPostState.value = newPostState.value.copy(suggestions = suggestions)
                        newPostLoadingState.value =
                            newPostLoadingState.value.copy(autocompleteLoadingState = false)

                    }
                }
            }
        }
    }

    fun getDirection() {
        val destination = newPostState.value.toLocation
        val origin = newPostState.value.fromLocation
        if (destination != null && origin != null) {
            var waypoints = ""
            if (newPostState.value.waypoints.isNotEmpty()) {
                newPostState.value.waypoints.onEach {
                    waypoints += "place_id:${it.key}|"
                }
            }
            viewModelScope.launch {
                googleMapsApiRepository.getDirection(
                    destination = "${destination.latitude},${destination.longitude}",
                    origin = "${origin.latitude},${origin.longitude}",
                    waypoints = waypoints
                ).collect { response ->
                    when (response) {
                        is Response.Error -> {
                            newPostLoadingState.value =
                                newPostLoadingState.value.copy(directionLoadingState = false)
                            _eventFlow.emit(NewPostUiEvent.ShowSnackBar(response.message))
                        }
                        is Response.Loading -> {
                            newPostLoadingState.value =
                                newPostLoadingState.value.copy(directionLoadingState = true)
                        }
                        is Response.Success -> {
                            newPostState.value = newPostState.value.copy(
                                direction = response.data,
                                currentRoute = response.data.routes[0]
                            )
                            newPostLoadingState.value =
                                newPostLoadingState.value.copy(directionLoadingState = false)
                        }
                    }
                }
            }
        }
    }

    fun getSelectedDirection(route: Route) {
        newPostState.value = newPostState.value.copy(currentRoute = route)
    }

    fun getReverseLocation(latLng: LatLng) {
        val value = "${latLng.latitude},${latLng.longitude}"
        viewModelScope.launch {
            googleMapsApiRepository.getReverseLocation(value).collect { response ->
                when (response) {
                    is Response.Error -> {
                        newPostLoadingState.value =
                            newPostLoadingState.value.copy(reverseLocationLoadingState = false)
                        _eventFlow.emit(NewPostUiEvent.ShowSnackBar(response.message))
                    }
                    is Response.Loading -> {
                        newPostLoadingState.value =
                            newPostLoadingState.value.copy(reverseLocationLoadingState = true)
                    }
                    is Response.Success -> {
                        newPostLoadingState.value =
                            newPostLoadingState.value.copy(reverseLocationLoadingState = false)
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
        if (placeId != null) {
            viewModelScope.launch {
                googleMapsApiRepository.getLocation(placeId).collect { response ->
                    when (response) {
                        is Response.Error -> {
                            newPostLoadingState.value =
                                newPostLoadingState.value.copy(locationLoadingState = false)
                            _eventFlow.emit(NewPostUiEvent.ShowSnackBar(response.message))
                        }
                        is Response.Loading -> {
                            newPostLoadingState.value =
                                newPostLoadingState.value.copy(locationLoadingState = true)
                        }
                        is Response.Success -> {
                            newPostLoadingState.value =
                                newPostLoadingState.value.copy(locationLoadingState = false)

                            var location: LatLng? = null
                            var placeId = ""
                            response.data.results.map {result->
                                placeId=result.place_id
                                location =
                                    LatLng(result.geometry.location.lat, result.geometry.location.lng)
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
                                LocationState.NewLocation -> {
                                    newPostState.value =
                                        newPostState.value.copy(
                                            geocoding = response.data,
                                            newLocation = location,
                                            newLocationPlaceId = placeId
                                        )
                                }
                            }
                        }
                    }
                }
            }
        } else {
            when (locationState) {
                LocationState.From -> {
                    newPostState.value =
                        newPostState.value.copy(
                            fromLocation = null
                        )
                }
                LocationState.To -> {
                    newPostState.value =
                        newPostState.value.copy(
                            toLocation = null
                        )
                }
                LocationState.NewLocation -> {
                    newPostState.value =
                        newPostState.value.copy(
                            newLocation = null
                        )
                }
            }
        }
    }

    fun addWaypoint() {
        val placeId = newPostState.value.newLocationPlaceId
        val location = newPostState.value.newLocation
        if (placeId.isNotEmpty() && location != null) {
            val waypoints = newPostState.value.waypoints
            waypoints[placeId] = location

            newPostState.value = newPostState.value.copy(
                waypoints = waypoints,
                newLocationPlaceId = "",
                newLocation = null,
                newLocationText = ""
            )
        }
    }


    sealed class NewPostUiEvent {
        data class ShowSnackBar(val message: String) : NewPostUiEvent()
        object DirectionReady : NewPostUiEvent()
    }
}