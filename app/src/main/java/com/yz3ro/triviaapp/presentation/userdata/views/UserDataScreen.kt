package com.yz3ro.triviaapp.presentation.userdata.views


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.yz3ro.triviaapp.R
import com.yz3ro.triviaapp.presentation.userdata.UserDataViewModel
import com.yz3ro.triviaapp.util.Resource
import timber.log.Timber

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UserDataScreen() {
    val viewModel: UserDataViewModel = hiltViewModel()
    val addDataState = viewModel.addDataState.collectAsState().value
    val pages = 3
    val pagerState = rememberPagerState()
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    LaunchedEffect(addDataState) {
        when (addDataState) {
            is Resource.Success -> {
                // Handle success
            }
            is Resource.Error -> {
                // Handle error
            }
            is Resource.Loading -> {
                // Handle loading
            }
            else -> {}
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.onboarding_bg_color)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Page Content
            HorizontalPager(
                count = pages,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                when (page) {
                    0 -> EnterNamePage(name)
                    1 -> EnterAgePage(age)
                    2 -> StartButtonPage(name.value, age.value,viewModel)
                }
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp),
                activeColor = colorResource(id = R.color.onboarding_active_dot_color),
                inactiveColor = colorResource(id = R.color.onboarding_inactive_dot_color)
            )
        }
    }
}

@Composable
fun EnterNamePage(name: MutableState<String>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Enter your name") },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun EnterAgePage(age: MutableState<String>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = age.value,
            onValueChange = { age.value = it },
            label = { Text("Enter your age") },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun StartButtonPage(name: String, age: String,viewModel: UserDataViewModel) {
    Button(
        onClick = {
            Timber.d("kullanıcı adı : $name, yaş : $age")
            viewModel.saveUserData(name, age)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text("Let's Get Started!")
    }
}