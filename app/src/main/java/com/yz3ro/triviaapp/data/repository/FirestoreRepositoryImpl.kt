package com.yz3ro.triviaapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.yz3ro.triviaapp.domain.repository.FirestoreRepository
import com.yz3ro.triviaapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirestoreRepositoryImpl(private val firestore: FirebaseFirestore) : FirestoreRepository {
    override fun addUser(uid: String, email: String): Flow<Resource<Void>> = flow {
        emit(Resource.Loading())
        val result = firestore.collection("users").document(uid)
            .set(mapOf("email" to email)).await()
        emit(Resource.Success(result))
    }.catch { exception ->
        emit(Resource.Error(exception.message ?: "Bilinmeyen bir hata oluştu."))
    }


    override fun saveUserData(uid: String, username: String, age: String): Flow<Resource<Void>> =
        flow {
            emit(Resource.Loading())
            val userData = hashMapOf(
                "name" to username,
                "age" to age
            )
            val result = firestore.collection("users").document(uid).set(userData, SetOptions.merge()).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
}