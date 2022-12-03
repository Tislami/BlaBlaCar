package com.zeroone.blablacar.domain.usecases.auth

import com.zeroone.blablacar.domain.repository.AuthRepository

class CreateUser(private val repository: AuthRepository) {
    operator fun invoke(email: String , password: String) =
        repository.createUser(email = email, password=password)
}