package com.yz3ro.triviaapp.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.yz3ro.triviaapp.domain.repository.AuthRepository
import com.yz3ro.triviaapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(private val firebaseAuth: FirebaseAuth) : AuthRepository {

    override fun signUp(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        emit(value = Resource.Loading())
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        emit(value = Resource.Success(data = result))
    }.catch { exception ->
        val errorMessage = when (exception.message) {
            "The email address is badly formatted." -> "E-posta adresi hatalı formatta."
            "The given password is invalid. [ Password should be at least 6 characters ]" -> "Geçersiz şifre. [ Şifre en az 6 karakter olmalıdır ]"
            "The email address is already in use by another account." -> "E-posta adresi zaten başka bir hesap tarafından kullanılıyor."
            else -> "Bilinmeyen bir hata oluştu."
        }
        emit(value = Resource.Error(errorMessage))
    }

    override fun signIn(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        emit(value = Resource.Loading())
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        emit(value = Resource.Success(data = result))
    }.catch {exception ->
        val errorMessage = when (exception.message) {
            "The email address is badly formatted." -> "E-posta adresi hatalı formatta."
            "The given password is invalid. [ Password should be at least 6 characters ]" -> "Geçersiz şifre. [ Şifre en az 6 karakter olmalıdır ]"
            "The email address is already in use by another account." -> "E-posta adresi zaten başka bir hesap tarafından kullanılıyor."
            else -> "Bilinmeyen bir hata oluştu."
        }
        emit(value = Resource.Error(errorMessage))
    }
}