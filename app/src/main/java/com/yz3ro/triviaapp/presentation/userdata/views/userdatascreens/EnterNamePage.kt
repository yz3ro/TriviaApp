package com.yz3ro.triviaapp.presentation.userdata.views.userdatascreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yz3ro.triviaapp.R
import com.yz3ro.triviaapp.presentation.userdata.UserDataViewModel
import com.yz3ro.triviaapp.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun EnterNamePage(
    name: MutableState<String>,
    viewModel: UserDataViewModel,
    coroutineScope: CoroutineScope
) {
    var lastCheckedUsername by remember { mutableStateOf("") }
    val usernameCheckState = viewModel.usernameCheckState.collectAsState().value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(350.dp, 420.dp)
                .padding(all = 20.dp)
                .background(
                    colorResource(id = R.color.userdata_box),
                    shape = RoundedCornerShape(16.dp)
                ), contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.test_tube),
                    contentDescription = "",
                    modifier = Modifier.size(74.dp, 74.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.choose_username),
                    color = Color.White
                )
                OutlinedTextField(
                    value = name.value,
                    onValueChange = {
                        name.value = it
                        coroutineScope.launch {
                            delay(500)
                            if (name.value != lastCheckedUsername) {
                                lastCheckedUsername = name.value
                                viewModel.checkUsernameAvailability(name.value)
                            }
                        }
                    },
                    label = { Text(stringResource(id = R.string.enter_name), color = Color.White) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White
                    )
                )
                when (usernameCheckState) {
                    is Resource.Loading -> {
                        Text("Checking...", color = Color.Gray, fontSize = 12.sp)
                    }
                    is Resource.Success -> {
                        Text("Username available", color = Color.Green, fontSize = 12.sp)
                        viewModel.usernameAvailable.value = usernameCheckState.data
                    }
                    is Resource.Error -> {
                        Text(stringResource(id = R.string.username_already), color = Color.Red, fontSize = 12.sp)
                        viewModel.usernameAvailable.value = false
                    }
                }
            }
        }
    }
}