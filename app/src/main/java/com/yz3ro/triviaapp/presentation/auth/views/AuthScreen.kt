package com.yz3ro.triviaapp.presentation.auth.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yz3ro.triviaapp.R
import com.yz3ro.triviaapp.presentation.Screen
import com.yz3ro.triviaapp.presentation.auth.AuthViewModel
import com.yz3ro.triviaapp.util.Resource
import timber.log.Timber

@Composable
fun AuthScreen(navController: NavController) {
    val viewModel: AuthViewModel = hiltViewModel()
    val signUpState = viewModel.signUpState.collectAsState().value
    val addUserState by viewModel.addUserState.collectAsState()
    val signInState = viewModel.signInState.collectAsState().value

    var passwordVisibility by remember { mutableStateOf(false) }
    var isLoginSelected by remember { mutableStateOf(true) }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(signUpState) {
        signUpState?.let {
            when (it) {
                is Resource.Success -> {
                    isLoginSelected = true
                }

                is Resource.Error -> {
                    errorMessage = it.message ?: "Bilinmeyen bir hata oluştu."
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
    LaunchedEffect(signInState) {
        signInState?.let {
            when (it) {
                is Resource.Success -> {
                    navController.navigate(Screen.UserDataScreen.route)
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

    Row(modifier = Modifier.fillMaxSize()) {
        // Left Navigation Bar
        Column(
            modifier = Modifier
                .background(
                    if (isLoginSelected) colorResource(id = R.color.sign_navbar_bg) else colorResource(
                        id = R.color.sign_up_navbar_bg
                    )
                )
                .width(75.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Kayıt Ol",
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { isLoginSelected = false }
                    .rotate(90f),
                textAlign = TextAlign.Center,
                fontWeight = if (isLoginSelected) FontWeight.Normal else FontWeight.Bold,
                fontSize = 16.sp,

                )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Giriş yap",
                color = Color.White,
                modifier = Modifier
                    .padding(5.dp)
                    .clickable { isLoginSelected = true }
                    .rotate(90f),
                textAlign = TextAlign.Center,
                fontWeight = if (isLoginSelected) FontWeight.Bold else FontWeight.Normal,
                fontSize = 16.sp
            )
        }

        // Main Content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (isLoginSelected) colorResource(id = R.color.sign_navbar_bg) else colorResource(
                        id = R.color.sign_up_navbar_bg
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 25.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            bottomStart = 30.dp
                        )
                    )
                    .background(
                        if (isLoginSelected) colorResource(id = R.color.sign_content_bg) else colorResource(
                            id = R.color.sign_up_content_bg
                        )
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = if (isLoginSelected) "Giriş Yap" else "Kayıt Ol",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(40.dp))

                androidx.compose.material3.OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Mail adres", color = Color.White) },
                    shape = CircleShape,
                    singleLine = true,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.8f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White
                    )
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(16.dp)
                ) {
                    androidx.compose.material3.OutlinedTextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = { Text("Şifre", color = Color.White) },
                        shape = CircleShape,
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White
                        )
                    )

                    IconButton(
                        onClick = { passwordVisibility = !passwordVisibility },
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(30.dp)
                            .padding(end = 10.dp)
                    ) {
                        Icon(
                            painter = if (passwordVisibility) painterResource(id = R.drawable.eye) else painterResource(
                                id = R.drawable.visible
                            ),
                            contentDescription = if (passwordVisibility) "Hide password" else "Show password",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }

                if (!isLoginSelected) {
                    androidx.compose.material3.OutlinedTextField(
                        value = confirmPassword.value,
                        onValueChange = { confirmPassword.value = it },
                        label = { Text("Şifre onayla", color = Color.White) },
                        shape = CircleShape,
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(0.8f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = MaterialTheme.colors.error)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Button(
                    onClick = {
                        if (isLoginSelected) {
                            Timber.d("giriş yap  ${email.value}-${password.value}")
                            viewModel.signIn(email.value, password.value)
                        } else {
                            Timber.d("kayıt ol ${email.value}-${password.value}")
                            if (password.value == confirmPassword.value) {
                                viewModel.signUp(email.value, password.value)
                            } else {
                                errorMessage = "Şifreler uyuşmuyor!"
                            }
                        }
                    },
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.8f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        if (isLoginSelected) colorResource(id = R.color.sign_navbar_bg) else colorResource(
                            id = R.color.sign_up_navbar_bg
                        )
                    )
                ) {
                    Text(
                        text = if (isLoginSelected) "Giriş Yap" else "Kayıt Ol",
                        color = Color.White
                    )
                }
            }
        }
    }
}

