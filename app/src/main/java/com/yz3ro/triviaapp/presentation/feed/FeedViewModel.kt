package com.yz3ro.triviaapp.presentation.feed

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(): ViewModel() {
    var selectedIndex = mutableStateOf(0)
        private set

    fun setSelectedIndex(index: Int) {
        selectedIndex.value = index
    }
}