package com.yz3ro.triviaapp.domain.use_case.sign_in

import com.google.firebase.auth.AuthResult
import com.yz3ro.triviaapp.domain.repository.AuthRepository
import com.yz3ro.triviaapp.util.Resource
import kotlinx.coroutines.flow.Flow

class SignInUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email:String,password:String): Flow<Resource<AuthResult>> {
        return authRepository.signIn(email,password)
    }
}