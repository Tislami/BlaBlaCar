package com.zeroone.blablacar.presentation.screens.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroone.blablacar.domain.model.Response
import com.zeroone.blablacar.domain.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val useCase: UseCase) :
    ViewModel() {

    var authStates = mutableStateOf(AuthState())
        private set

    private val _loginState = MutableSharedFlow<LoginState>()
    val loginState = _loginState.asSharedFlow()

    private val _registrationState = MutableSharedFlow<RegistrationState>()
    val registrationState = _registrationState.asSharedFlow()

    private var loginJob: Job? = null
    val isLoading = mutableStateOf(false)

    fun setEmail(value: String) {
        authStates.value = authStates.value.copy(eMail = value)
    }

    fun setPassword(value: String) {
        authStates.value = authStates.value.copy(password = value)
    }

    fun login(email: String, password: String) {
        loginJob?.cancel()
        loginJob = viewModelScope.launch {
            useCase.authUseCase.login(email, password).collect { response ->
                when (response) {
                    is Response.Error -> {
                        isLoading.value=false
                        _loginState.emit(LoginState.Error(response.message))
                    }
                    is Response.Loading -> { isLoading.value=true }
                    is Response.Success -> {
                        isLoading.value=false
                        _loginState.emit(LoginState.Logged)
                    }
                }
            }
        }
    }

    fun createUser(email: String, password: String) {
        viewModelScope.launch {
            useCase.authUseCase.createUser(email, password).collect { response ->
                when (response) {
                    is Response.Error -> {
                        isLoading.value = false
                        _registrationState.emit(RegistrationState.Error(response.message))
                    }
                    is Response.Loading -> { isLoading.value = true }
                    is Response.Success -> {
                        isLoading.value = false
                        _registrationState.emit(RegistrationState.Created)
                    }
                }
            }
        }
    }
}