package com.yz3ro.triviaapp.presentation.feed.views

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.ShapeCornerRadius
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable
import com.google.firebase.auth.FirebaseAuth
import com.yz3ro.triviaapp.R
import com.yz3ro.triviaapp.presentation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(navController: NavController) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val navigationBarItems = remember { NavigationBarItems.entries.toTypedArray() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "",
                        modifier = Modifier.size(35.dp)
                    )
                }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(id = R.color.topappbar_bg)
                ), actions = {
                    Row(
                        modifier = Modifier
                            .size(69.dp, 35.dp)
                            .background(Color.White, shape = RoundedCornerShape(26.dp))
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(26.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dollar),
                            contentDescription = "",
                            modifier = Modifier.size(18.dp)
                        )
                        Text(text = "10", fontSize = 14.sp)
                    }

                }
            )
        },
        bottomBar = {
            AnimatedNavigationBar(
                modifier = Modifier.height(64.dp),
                selectedIndex = selectedIndex,
                cornerRadius = shapeCornerRadius(cornerRadius = 34.dp),
                ballAnimation = Parabolic(tween(300)),
                indentAnimation = Height(tween(300)),
                barColor = colorResource(id = R.color.bottomnavbar_box),
                ballColor = colorResource(id = R.color.topappbar_bg)
            ) {
                navigationBarItems.forEach { item ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .noRippleClickable { selectedIndex = item.ordinal },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = "",
                            modifier = Modifier.size(26.dp),
                            tint = if (selectedIndex == item.ordinal) Color.White else Color.White
                        )
                    }
                }
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(colorResource(id = R.color.feed_bg))
            ) {
                when (selectedIndex) {
                    0 -> Page1()
                    1 -> Page2()
                    2 -> Page3()
                    3 -> Page4(navController)
                }
            }
        }
    )
}

enum class NavigationBarItems(@DrawableRes val icon: Int) {
    Home(R.drawable.home), // drawable klasöründeki home_icon dosyası
    Category(R.drawable.category),
    Crown(R.drawable.crown),
    Profile(R.drawable.profile)
}

@SuppressLint("ModifierFactoryUnreferencedReceiver", "UnnecessaryComposedModifier")
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}


@Composable
fun Page1() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sayfa 1", fontSize = 50.sp)
    }
}

@Composable
fun Page2() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sayfa 2", fontSize = 50.sp)
    }
}

@Composable
fun Page3() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sayfa 3", fontSize = 50.sp)
    }
}

@Composable
fun Page4(navController: NavController) {
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

