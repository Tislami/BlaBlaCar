package com.zeroone.blablacar.di

import com.zeroone.blablacar.data.remote.FirebaseDatabase
import com.zeroone.blablacar.data.remote.google_maps.DirectionApi
import com.zeroone.blablacar.data.remote.google_maps.GeocodingApi
import com.zeroone.blablacar.data.remote.google_maps.ReverseGeocodingApi
import com.zeroone.blablacar.data.remote.google_maps.geo_place.AutocompleteApi
import com.zeroone.blablacar.data.remote.google_maps.geo_place.FindPlaceApi
import com.zeroone.blablacar.domain.repository.*
import com.zeroone.blablacar.domain.repository.google_maps.GoogleMapsApiRepository
import com.zeroone.blablacar.domain.repository.google_maps.GoogleMapsApiRepositoryImpl
import com.zeroone.blablacar.domain.usecases.UseCase
import com.zeroone.blablacar.domain.usecases.auth.AuthUseCase
import com.zeroone.blablacar.domain.usecases.auth.CreateUser
import com.zeroone.blablacar.domain.usecases.auth.GetAuthState
import com.zeroone.blablacar.domain.usecases.auth.Login
import com.zeroone.blablacar.utils.GOOGLE_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase()


    @Provides
    @Singleton
    fun provideDirectionApi() : DirectionApi {
        return Retrofit.Builder()
            .baseUrl(GOOGLE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DirectionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideReverseGeocodingApi() : ReverseGeocodingApi {
        return Retrofit.Builder()
            .baseUrl(GOOGLE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReverseGeocodingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGeocodingApi() : GeocodingApi {
        return Retrofit.Builder()
            .baseUrl(GOOGLE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeocodingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGeoPlaceAutocompleteApi() : AutocompleteApi {
        return Retrofit.Builder()
            .baseUrl(GOOGLE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AutocompleteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGeoPlaceFindPlaceApi() : FindPlaceApi {
        return Retrofit.Builder()
            .baseUrl(GOOGLE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FindPlaceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGeoPlaceRepository(
        autocompleteApi: AutocompleteApi,
        findPlaceApi: FindPlaceApi,
        directionApi: DirectionApi,
        geocodingApi: GeocodingApi,
        reverseGeocodingApi: ReverseGeocodingApi,
    ): GoogleMapsApiRepository {
        return GoogleMapsApiRepositoryImpl(
            autocompleteApi,
            findPlaceApi,
            directionApi,
            reverseGeocodingApi,
            geocodingApi,
            )
    }


    @Provides
    @Singleton
    fun provideAuthRepository(firebaseDatabase: FirebaseDatabase): AuthRepository {
        return AuthRepositoryImpl(firebaseDatabase.auth)
    }
    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCase(
            createUser = CreateUser(authRepository),
            login = Login(authRepository),
            getAuthState = GetAuthState(authRepository),
        )
    }
    @Provides
    @Singleton
    fun provideUseCase(
        authUseCase: AuthUseCase,
    ): UseCase {
        return UseCase(
            authUseCase = authUseCase
        )
    }
}