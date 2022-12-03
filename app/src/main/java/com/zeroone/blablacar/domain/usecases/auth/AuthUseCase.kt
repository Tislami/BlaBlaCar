package com.zeroone.blablacar.domain.usecases.auth

data class AuthUseCase (
    val createUser: CreateUser,
    val getAuthState: GetAuthState,
    val login: Login,
)