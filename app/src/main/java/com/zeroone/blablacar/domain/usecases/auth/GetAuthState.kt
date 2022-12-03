package com.zeroone.blablacar.domain.usecases.auth

import com.zeroone.blablacar.domain.repository.AuthRepository

class GetAuthState(private val repository: AuthRepository)
{
    operator fun invoke() = repository.getAuthState()
}