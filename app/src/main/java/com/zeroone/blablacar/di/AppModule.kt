package com.zeroone.blablacar.di

import com.zeroone.blablacar.data.remote.FirebaseDatabase
import com.zeroone.blablacar.domain.repository.AuthRepository
import com.zeroone.blablacar.domain.repository.AuthRepositoryImpl
import com.zeroone.blablacar.domain.usecases.UseCase
import com.zeroone.blablacar.domain.usecases.auth.AuthUseCase
import com.zeroone.blablacar.domain.usecases.auth.CreateUser
import com.zeroone.blablacar.domain.usecases.auth.GetAuthState
import com.zeroone.blablacar.domain.usecases.auth.Login
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase()



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