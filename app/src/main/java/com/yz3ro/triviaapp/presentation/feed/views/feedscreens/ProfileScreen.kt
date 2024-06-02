package com.yz3ro.triviaapp.presentation.feed.views.feedscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.yz3ro.triviaapp.presentation.Screen

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sayfa 4", fontSize = 50.sp)
        Button(onClick = {
            FirebaseAuth.getInstance().signOut()
            navController.navigate(Screen.OnBoardingScreen.route)
        }) {
            Text(text = "çıkış yap", fontSize = 50.sp)

        }
    }
}
