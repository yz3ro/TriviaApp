package com.yz3ro.triviaapp.presentation.userdata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.yz3ro.triviaapp.domain.use_case.add_data.AddDataUseCase
import com.yz3ro.triviaapp.domain.use_case.add_user.AddUserUseCase
import com.yz3ro.triviaapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(
    private val addDataUseCase: AddDataUseCase,
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
}