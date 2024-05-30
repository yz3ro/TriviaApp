package com.yz3ro.triviaapp.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.yz3ro.triviaapp.domain.use_case.sign_in.SignInUseCase
import com.yz3ro.triviaapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInUseCase: SignInUseCase) : ViewModel() {
    private val _signInState = MutableStateFlow<Resource<AuthResult>?>(null)
    val signInState: StateFlow<Resource<AuthResult>?> = _signInState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            signInUseCase(email, password).collect { result ->
                _signInState.value = result
            }
        }
    }
}