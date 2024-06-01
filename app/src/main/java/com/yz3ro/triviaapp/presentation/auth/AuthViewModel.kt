package com.yz3ro.triviaapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.yz3ro.triviaapp.domain.use_case.add_user.AddUserUseCase
import com.yz3ro.triviaapp.domain.use_case.sign_in.SignInUseCase
import com.yz3ro.triviaapp.domain.use_case.sign_up.SignUpUseCase
import com.yz3ro.triviaapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    private val _signInState = MutableStateFlow<Resource<AuthResult>?>(null)
    val signInState: StateFlow<Resource<AuthResult>?> = _signInState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            signInUseCase(email, password).collect { result ->
                _signInState.value = result
            }
        }
    }

    private val _signUpState = MutableStateFlow<Resource<AuthResult>?>(null)
    val signUpState: StateFlow<Resource<AuthResult>?> = _signUpState

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            signUpUseCase(email, password).collect { result ->
                _signUpState.value = result
                if (result is Resource.Success) {
                    result.data?.user?.let { user ->
                        addUser(user.uid, email)
                    }
                }
            }
        }
    }

    private val _addUserState = MutableStateFlow<Resource<Void>?>(null)
    val addUserState: StateFlow<Resource<Void>?> = _addUserState

    private fun addUser(uid: String, email: String) {
        viewModelScope.launch {
            addUserUseCase(uid, email).collect { result ->
                _addUserState.value = result
            }
        }
    }
}