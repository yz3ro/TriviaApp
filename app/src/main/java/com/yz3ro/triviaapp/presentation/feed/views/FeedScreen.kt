package com.yz3ro.triviaapp.presentation.feed.views

import LeaderBoardScreen
import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable
import com.yz3ro.triviaapp.R
import com.yz3ro.triviaapp.presentation.feed.FeedViewModel
import com.yz3ro.triviaapp.presentation.feed.views.feedscreens.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(navController: NavController) {
    val navigationBarItems = remember { NavigationBarItems.entries.toTypedArray() }
    val viewModel : FeedViewModel = hiltViewModel()
    var selectedIndex = viewModel.selectedIndex.value

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
            Box {
                AnimatedNavigationBar(
                    modifier = Modifier.height(64.dp),
                    selectedIndex = selectedIndex,
                    cornerRadius = shapeCornerRadius(cornerRadius = 34.dp),
                    ballAnimation = Parabolic(tween(300)),
                    indentAnimation = Height(tween(300)),
                    barColor = colorResource(id = R.color.bottomnavbar_box),
                    ballColor = colorResource(id = R.color.topappbar_bg)
                ) {
                    navigationBarItems.forEachIndexed { index, item ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .noRippleClickable { viewModel.setSelectedIndex(index) },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = "",
                                modifier = Modifier.size(26.dp),
                                tint = if (selectedIndex == index) Color.White else Color.White
                            )
                        }
                    }
                }
                FloatingActionButton(
                    onClick = {
                              // fab click
                              },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-30).dp),
                    backgroundColor = Color.White
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = "Quiz",
                        tint = Color.Unspecified
                    )
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
                    0 -> HomeScreen()
                    1 -> CategoriesScreen()
                    2 -> LeaderBoardScreen()
                    3 -> ProfileScreen(navController)
                }
            }
        }
    )
}

enum class NavigationBarItems(@DrawableRes val icon: Int) {
    Home(R.drawable.home),
    Category(R.drawable.category),
    Crown(R.drawable.crown),
    Profile(R.drawable.profile)
}











