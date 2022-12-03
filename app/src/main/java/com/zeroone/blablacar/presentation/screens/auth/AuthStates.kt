package com.zeroone.blablacar.presentation.screens.auth


data class AuthState(
    val eMail : String="",
    val password : String = ""
)

sealed interface LoginState {
    data class Error(val message: String) : LoginState
    object Logged : LoginState
}

sealed interface RegistrationState {
    data class Error(val message: String) : RegistrationState
    object Created : RegistrationState
}
