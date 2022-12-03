package com.zeroone.blablacar.domain.usecases

import com.zeroone.blablacar.domain.usecases.auth.AuthUseCase

data class UseCase(
    var authUseCase: AuthUseCase)
