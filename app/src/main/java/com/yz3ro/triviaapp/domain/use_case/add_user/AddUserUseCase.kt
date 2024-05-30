package com.yz3ro.triviaapp.domain.use_case.add_user

import com.yz3ro.triviaapp.domain.repository.FirestoreRepository
import com.yz3ro.triviaapp.util.Resource
import kotlinx.coroutines.flow.Flow

class AddUserUseCase(private val firestoreRepository: FirestoreRepository) {
    suspend operator fun invoke(uid: String, email: String): Flow<Resource<Void>> {
        return firestoreRepository.addUser(uid, email)
    }
}