package com.yz3ro.triviaapp.domain.repository

import com.google.firebase.firestore.QuerySnapshot
import com.yz3ro.triviaapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {
    fun addUser(uid: String, email: String): Flow<Resource<Void>>
    fun saveUserData(uid:String,username:String,age:String) : Flow<Resource<Void>>

    fun isUsernameAvailable(username:String) : Flow<Resource<Boolean>>

}