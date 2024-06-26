package com.yz3ro.triviaapp.presentation.userdata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QuerySnapshot
import com.yz3ro.triviaapp.domain.use_case.add_data.AddDataUseCase
import com.yz3ro.triviaapp.domain.use_case.add_user.AddUserUseCase
import com.yz3ro.triviaapp.domain.use_case.check_username.CheckUserNameUseCase
import com.yz3ro.triviaapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(
    private val addDataUseCase: AddDataUseCase,
    private val checkUserNameUseCase : CheckUserNameUseCase,
    private val auth: FirebaseAuth
) : ViewModel(){

    private val _addDataState = MutableStateFlow<Resource<Void>?>(null)
    val addDataState: StateFlow<Resource<Void>?> = _addDataState

    fun saveUserData(name: String, age: String) {
        val uid = auth.currentUser?.uid
        if (uid != null) {
            viewModelScope.launch {
                addDataUseCase(uid, name, age).collect { result ->
                    _addDataState.value = result
                }
            }
        }
    }

    private val _usernameCheckState = MutableStateFlow<Resource<Boolean>>(Resource.Loading())
    val usernameCheckState: StateFlow<Resource<Boolean>> = _usernameCheckState
    val usernameAvailable = MutableStateFlow<Boolean?>(null)
    fun checkUsernameAvailability(username: String) {
        viewModelScope.launch {
            checkUserNameUseCase(username).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _usernameCheckState.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        val isAvailable = resource.data ?: false // Veri boşsa kullanıcı adı mevcut kabul edilir
                        _usernameCheckState.value = if (isAvailable) Resource.Success(true) else Resource.Error("Kullanıcı adı zaten alınmış")
                    }
                    is Resource.Error -> {
                        _usernameCheckState.value = Resource.Error("Kullanıcı adı kontrolünde hata oluştu")
                    }
                }
            }
        }
    }
}