package com.zeroone.blablacar.domain.usecases.auth

import com.zeroone.blablacar.domain.repository.AuthRepository


class Login(private val repository: AuthRepository) {
    operator fun invoke(email : String , password: String) = repository.login(email, password)
}