package com.yz3ro.triviaapp.domain.use_case.check_username

import com.google.firebase.firestore.QuerySnapshot
import com.yz3ro.triviaapp.domain.repository.FirestoreRepository
import com.yz3ro.triviaapp.util.Resource
import kotlinx.coroutines.flow.Flow

class CheckUserNameUseCase(private val firestoreRepository: FirestoreRepository) {
    suspend operator fun invoke(username:String): Flow<Resource<Boolean>> {
        return firestoreRepository.isUsernameAvailable(username)
    }
}