package com.zeroone.blablacar.presentation.screens.new_post

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.domain.model.Response
import com.zeroone.blablacar.domain.model.google_map.direction.GeocodedWaypoint
import com.zeroone.blablacar.domain.repository.PostRepository
import com.zeroone.blablacar.domain.repository.google_maps.GoogleMapsApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


//Sheki : ChIJOavewsaHR0ARTJ8mOt92cKU
//Agdam : ChIJux8yguSRPkARBxWipqpZS4I

@HiltViewModel
class GoogleMapsApiViewModel @Inject constructor(
    private val googleMapsApiRepository: GoogleMapsApiRepository, ) :
    ViewModel() {

    var mapState = mutableStateOf(GoogleMapsApiState())
        private set

    val mapLoadingState = mutableStateOf(MapsLoadingState())

    private val _eventFlow = MutableSharedFlow<GoogleMapsApiUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var autocompleteJob: Job? = null
    fun autocomplete(input: String) {
        autocompleteJob?.cancel()
        autocompleteJob = viewModelScope.launch {
            googleMapsApiRepository.autocomplete(input = input).collect { response ->
                when (response) {
                    is Response.Error -> {
                        mapLoadingState.value =
                            mapLoadingState.value.copy(autocomplete = false)
                        _eventFlow.emit(GoogleMapsApiUiEvent.Error(response.message))
                    }
                    is Response.Loading -> {
                        mapLoadingState.value =
                            mapLoadingState.value.copy(autocomplete = true)
                    }
                    is Response.Success -> {

                        val suggestions: MutableMap<String, String> = mutableMapOf()
                        response.data.predictions.map {
                            suggestions[it.description] = it.place_id
                        }
                        mapState.value = mapState.value.copy(suggestions = suggestions)
                        mapLoadingState.value =
                            mapLoadingState.value.copy(autocomplete = false)

                    }
                }
            }
        }
    }

    private var getDirectionJob: Job? = null
    fun getDirection(destination: LatLng?, origin: LatLng?, waypoints: String="") {
        if (destination != null && origin != null) {
            getDirectionJob?.cancel()
            getDirectionJob= viewModelScope.launch {
                googleMapsApiRepository.getDirection(
                    destination = "${destination.latitude},${destination.longitude}",
                    origin = "${origin.latitude},${origin.longitude}",
                    waypoints = waypoints
                ).collect { response ->
                    when (response) {
                        is Response.Error -> {
                            mapLoadingState.value =
                                mapLoadingState.value.copy(direction = false)
                            _eventFlow.emit(GoogleMapsApiUiEvent.Error(response.message))
                        }
                        is Response.Loading -> {
                            mapLoadingState.value =
                                mapLoadingState.value.copy(direction = true)
                        }
                        is Response.Success -> {
                            mapLoadingState.value =
                                mapLoadingState.value.copy(direction = false)

                            if (response.data.routes.isEmpty()) {
                                _eventFlow.emit(GoogleMapsApiUiEvent.Error("Direction alınamadı geri dönün"))
                            } else {
                                mapState.value = mapState.value.copy(direction = response.data)
                            }
                        }
                    }
                }
            }
        }
    }

    fun getReverseLocation(latLng: LatLng) {
        val value = "${latLng.latitude},${latLng.longitude}"
        viewModelScope.launch {
            googleMapsApiRepository.getReverseLocation(value).collect { response ->
                when (response) {
                    is Response.Error -> {
                        mapLoadingState.value =
                            mapLoadingState.value.copy(reverseLocation = false)
                        _eventFlow.emit(GoogleMapsApiUiEvent.Error(response.message))
                    }
                    is Response.Loading -> {
                        mapLoadingState.value =
                            mapLoadingState.value.copy(reverseLocation = true)
                    }
                    is Response.Success -> {
                        mapLoadingState.value = mapLoadingState.value.copy(reverseLocation = false)
                        val suggestions: MutableMap<String, String> = mutableMapOf()
                        response.data.results.map {
                            suggestions[it.formatted_address] = it.place_id
                        }
                        mapState.value = mapState.value.copy(suggestions = suggestions)
                    }
                }
            }
        }
    }

    fun getLocation(placeId: String?) {
        if (placeId != null) {
            viewModelScope.launch {
                googleMapsApiRepository.getLocation(placeId).collect { response ->
                    when (response) {
                        is Response.Error -> {
                            mapLoadingState.value = mapLoadingState.value.copy(location = false)
                            _eventFlow.emit(GoogleMapsApiUiEvent.Error(response.message))
                        }
                        is Response.Loading -> {
                            mapLoadingState.value = mapLoadingState.value.copy(location = true)
                        }
                        is Response.Success -> {
                            mapLoadingState.value = mapLoadingState.value.copy(location = false)
                            var location: LatLng? = null
                            var shortAddress = ""
                            var formattedAddress = ""
                            response.data.results.onEach { result ->
                                location = LatLng(
                                    result.geometry.location.lat,
                                    result.geometry.location.lng
                                )
                                formattedAddress = result.formatted_address

                                result.address_components.forEach { addressComponent ->
                                    shortAddress = addressComponent.short_name
                                }
                            }
                            if (location != null) {
                                mapState.value = mapState.value.copy(
                                    geocoding = response.data,
                                    currentLocation = mapState.value.currentLocation.copy(
                                        value = location,
                                        shortAddress = shortAddress,
                                        placeId = placeId,
                                        formatted_address = formattedAddress
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }


    sealed class GoogleMapsApiUiEvent {
        data class Error(val message: String) : GoogleMapsApiUiEvent()
    }
}