package com.yz3ro.triviaapp.presentation.userdata.views


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.yz3ro.triviaapp.R
import com.yz3ro.triviaapp.presentation.Screen
import com.yz3ro.triviaapp.presentation.userdata.UserDataViewModel
import com.yz3ro.triviaapp.presentation.userdata.views.userdatascreens.EnterAgePage
import com.yz3ro.triviaapp.presentation.userdata.views.userdatascreens.EnterNamePage
import com.yz3ro.triviaapp.presentation.userdata.views.userdatascreens.StartButtonPage
import com.yz3ro.triviaapp.util.Resource
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun UserDataScreen(navController: NavController) {
    val viewModel: UserDataViewModel = hiltViewModel()
    val addDataState = viewModel.addDataState.collectAsState().value
    val coroutineScope = rememberCoroutineScope()
    val pages = 3
    val pagerState = rememberPagerState()
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val usernameAvailable by viewModel.usernameAvailable.collectAsState()

    LaunchedEffect(addDataState) {
        when (addDataState) {
            is Resource.Success -> {
                navController.navigate(Screen.FeedScreen.route)
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
        color = colorResource(id = R.color.userdata_bg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "",
                modifier = Modifier.size(110.dp, 123.dp)
            )
            Text(
                text = stringResource( R.string.app_name),
                color = colorResource(id = R.color.onboarding_active_dot_color),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Page Content
            HorizontalPager(
                count = pages,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                when (page) {
                    0 -> EnterNamePage(name, viewModel, coroutineScope)
                    1 -> EnterAgePage(age)
                    2 -> StartButtonPage()
                }
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp),
                activeColor = colorResource(id = R.color.onboarding_active_dot_color),
                inactiveColor = colorResource(id = R.color.userdata_box)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (pagerState.currentPage > 0) {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            if (pagerState.currentPage > 0) {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    }) {
                        androidx.compose.material.Icon(
                            tint = colorResource(id = R.color.brush2),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(48.dp))
                }

                if (pagerState.currentPage < pages - 1) {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            if (pagerState.currentPage < pages - 1) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    }) {
                        androidx.compose.material.Icon(
                            tint = colorResource(id = R.color.brush2),
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null
                        )
                    }
                } else {
                    OutlinedButton(
                        onClick = {
                            viewModel.saveUserData(name.value, age.value)
                        },
                        border = BorderStroke(1.dp, colorResource(id = R.color.brush2)),
                        enabled = usernameAvailable == true
                    ) {
                        Text(
                            text = stringResource(R.string.start),
                            color = colorResource(id = R.color.brush2),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}