package com.yz3ro.triviaapp.domain.use_case.add_data

import com.yz3ro.triviaapp.domain.repository.FirestoreRepository
import com.yz3ro.triviaapp.util.Resource
import kotlinx.coroutines.flow.Flow

class AddDataUseCase (private val firestoreRepository: FirestoreRepository) {
    suspend operator fun invoke(uid:String,username:String,age:String): Flow<Resource<Void>> {
        return firestoreRepository.saveUserData(uid,username,age)
    }
}