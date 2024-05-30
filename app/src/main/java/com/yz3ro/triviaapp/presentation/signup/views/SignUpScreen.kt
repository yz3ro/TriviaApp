package com.yz3ro.triviaapp.presentation.signup.views

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yz3ro.triviaapp.presentation.Screen
import com.yz3ro.triviaapp.presentation.signup.SignUpViewModel
import com.yz3ro.triviaapp.util.Resource

@Composable
fun SignUpScreen(navController: NavController) {
    val viewModel: SignUpViewModel = hiltViewModel()
    val signUpState = viewModel.signUpState.collectAsState().value
    val addUserState by viewModel.addUserState.collectAsState()


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(signUpState) {
        signUpState?.let {
            when (it) {
                is Resource.Success -> {
                    navController.navigate(Screen.SignInScreen.route)
                }
                is Resource.Error -> {
                    errorMessage = it.message ?: "Unknown error"
                }
                is Resource.Loading -> {
                    // Handle loading state if needed
                }
            }
        }
        addUserState?.let {
            when (it) {
                is Resource.Success -> {
                    // Handle successful Firestore write (e.g., navigate to a different screen)
                }
                is Resource.Error -> {
                    errorMessage = it.message ?: "Bilinmeyen bir hata oluştu."
                }
                is Resource.Loading -> {
                    // Handle loading state if needed
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Şifre") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Tekrar şifre") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colors.error)
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(
            onClick = {
                if (password == confirmPassword) {
                    viewModel.signUp(email, password)
                } else {
                    errorMessage = "Şifreler Uyuşmuyor!"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Kayıt ol")
        }
        Text(
            text = "Giriş yap!",
            modifier = Modifier.clickable {
                navController.navigate(Screen.SignInScreen.route)
            },
            fontSize = 10.sp
        )
    }
}