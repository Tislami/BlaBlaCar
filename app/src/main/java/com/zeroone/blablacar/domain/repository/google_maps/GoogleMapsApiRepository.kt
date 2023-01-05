package com.zeroone.blablacar.domain.repository.google_maps

import android.util.Log
import com.zeroone.blablacar.data.remote.google_maps.DirectionApi
import com.zeroone.blablacar.data.remote.google_maps.GeocodingApi
import com.zeroone.blablacar.data.remote.google_maps.ReverseGeocodingApi
import com.zeroone.blablacar.data.remote.google_maps.geo_place.AutocompleteApi
import com.zeroone.blablacar.data.remote.google_maps.geo_place.FindPlaceApi
import com.zeroone.blablacar.domain.model.Response
import com.zeroone.blablacar.domain.model.google_map.direction.Direction
import com.zeroone.blablacar.domain.model.google_map.geo_place.autocomplete.Autocomplete
import com.zeroone.blablacar.domain.model.google_map.geo_place.find_place.FindPlace
import com.zeroone.blablacar.domain.model.google_map.geocoding.Geocoding
import com.zeroone.blablacar.domain.model.google_map.reverse_geocoding.ReverseGeocoding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.SocketTimeoutException

interface GoogleMapsApiRepository {
    suspend fun autocomplete(input: String): Flow<Response<Autocomplete>>
    suspend fun findPlace(input: String): Flow<Response<FindPlace>>
    suspend fun getDirection(destination: String,origin: String): Flow<Response<Direction>>
    suspend fun getReverseLocation(latLng: String): Flow<Response<ReverseGeocoding>>
    suspend fun getLocation(placeId: String): Flow<Response<Geocoding>>
}

class GoogleMapsApiRepositoryImpl(
    private val autocompleteApi: AutocompleteApi,
    private val findPlaceApi: FindPlaceApi,
    private val directionApi: DirectionApi,
    private val reverseGeocodingApi: ReverseGeocodingApi,
    private val geocodingApi: GeocodingApi,
    ) :
    GoogleMapsApiRepository {
    override suspend fun autocomplete(input: String) = flow {
        emit(Response.Loading)
        try {
            val data = autocompleteApi.getPlaceAutocomplete(input)
            emit(Response.Success(data))

        } catch (e: HttpException) {
            emit(Response.Error("Oops something is wrong ${e.message}"))
        } catch (e: SocketTimeoutException) {
            emit(Response.Error("Oops something is wrong ${e.message}"))
        }
    }

    override suspend fun findPlace(input: String) = flow{
        emit(Response.Loading)
        try {
            val data = findPlaceApi.findPlaceFromText(input)
            emit(Response.Success(data))

        } catch (e: HttpException) {
            emit(Response.Error("Oops something is wrong ${e.message}"))
        } catch (e: SocketTimeoutException) {
            emit(Response.Error("Oops something is wrong ${e.message}"))
        }
    }

    override suspend fun getDirection(destination: String, origin: String)= flow {
        emit(Response.Loading)
        try {
            val data = directionApi.getDirection(destination = destination, origin= origin)
            emit(Response.Success(data))

        } catch (e: HttpException) {
            emit(Response.Error("Oops something is wrong ${e.message}"))
        } catch (e: SocketTimeoutException) {
            emit(Response.Error("Oops something is wrong ${e.message}"))
        }
    }

    override suspend fun getReverseLocation(latLng: String) = flow {
        emit(Response.Loading)
        try {
            val data = reverseGeocodingApi.getLocation(latLng)
            emit(Response.Success(data))
        } catch (e: HttpException) {
            emit(Response.Error("Oops something is wrong ${e.message}"))
        } catch (e: SocketTimeoutException) {
            emit(Response.Error("Oops something is wrong ${e.message}"))
        }
    }

    override suspend fun getLocation(placeId: String) = flow {
        emit(Response.Loading)
        try {
            val data = geocodingApi.getLocation(placeId)
            emit(Response.Success(data))
        } catch (e: HttpException) {
            emit(Response.Error("Oops something is wrong ${e.message}"))
        } catch (e: SocketTimeoutException) {
            emit(Response.Error("Oops something is wrong ${e.message}"))
        }
    }


}