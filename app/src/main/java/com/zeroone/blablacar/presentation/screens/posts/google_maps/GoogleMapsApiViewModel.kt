package com.zeroone.blablacar.presentation.screens.posts.google_maps

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
class GoogleMapsApiViewModel @Inject constructor(
    private val googleMapsApiRepository: GoogleMapsApiRepository
) :
    ViewModel() {

    var newPostState = mutableStateOf(NewPostState())
        private set

    val isLoading = mutableStateOf(false)

    private val _eventFlow = MutableSharedFlow<GoogleMapsApiUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var autocompleteJob: Job? = null

    fun findPlacea(input: String) {
        viewModelScope.launch {
            googleMapsApiRepository.findPlace(input = input).collect { response ->
                when (response) {
                    is Response.Error -> {
                        isLoading.value = false
                        _eventFlow.emit(GoogleMapsApiUiEvent.ShowSnackBar(response.message))
                    }
                    is Response.Loading -> {
                        isLoading.value = true
                    }
                    is Response.Success -> {
                        newPostState.value =
                            newPostState.value.copy(findPlace = response.data)
                    }
                }
            }
        }
    }

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

                        newPostState.value =
                            newPostState.value.copy(
                                autocomplete = response.data,
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

                            val routes = mutableListOf<List<LatLng>>()
                            newPostState.value.direction?.routes?.onEach { route ->
                                route.overview_polyline.points.let {
                                    routes.add(decodePoly(it))
                                }
                            }
                            newPostState.value = newPostState.value.copy(
                                direction=response.data,
                                polyLinesPoints = routes
                            )
                        }
                    }
                }
            }
    }

    fun getSelectedDirection(points : List<LatLng>) {
        val temp  = mutableListOf(points)
        newPostState.value.polyLinesPoints.map{
            if (it!=points){ temp.add(it) }
        }
        newPostState.value = newPostState.value.copy(polyLinesPoints = temp)
    }

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

    /**
     * Method to decode polyline points
     * Courtesy : https://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
     */
    private fun decodePoly(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val p = LatLng(
                lat.toDouble() / 1E5,
                lng.toDouble() / 1E5
            )
            poly.add(p)
        }
        return poly
    }

    sealed class GoogleMapsApiUiEvent {
        data class ShowSnackBar(val message: String) : GoogleMapsApiUiEvent()
    }
}