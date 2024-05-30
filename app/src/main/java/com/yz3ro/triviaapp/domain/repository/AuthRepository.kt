package com.yz3ro.triviaapp.domain.repository

import com.google.firebase.auth.AuthResult
import com.yz3ro.triviaapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signUp(email: String,password:String) : Flow<Resource<AuthResult>>

    fun signIn(email:String,password: String) : Flow<Resource<AuthResult>>
}