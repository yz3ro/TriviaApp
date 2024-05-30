package com.yz3ro.triviaapp.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.yz3ro.triviaapp.data.repository.AuthRepositoryImpl
import com.yz3ro.triviaapp.data.repository.FirestoreRepositoryImpl
import com.yz3ro.triviaapp.domain.repository.AuthRepository
import com.yz3ro.triviaapp.domain.repository.FirestoreRepository
import com.yz3ro.triviaapp.domain.use_case.add_data.AddDataUseCase
import com.yz3ro.triviaapp.domain.use_case.add_user.AddUserUseCase
import com.yz3ro.triviaapp.domain.use_case.sign_in.SignInUseCase
import com.yz3ro.triviaapp.domain.use_case.sign_up.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideFirestoreRepository(firestore: FirebaseFirestore): FirestoreRepository {
        return FirestoreRepositoryImpl(firestore)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase {
        return SignUpUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSignInUseCase(authRepository: AuthRepository): SignInUseCase {
        return SignInUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideAddUserUseCase(firestoreRepository: FirestoreRepository): AddUserUseCase {
        return AddUserUseCase(firestoreRepository)
    }
    @Provides
    @Singleton
    fun provideAddDataUseCase(firestoreRepository: FirestoreRepository): AddDataUseCase {
        return AddDataUseCase(firestoreRepository)
    }
}