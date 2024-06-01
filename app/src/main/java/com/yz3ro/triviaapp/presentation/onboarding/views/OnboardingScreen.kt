package com.yz3ro.triviaapp.presentation.onboarding.views


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.yz3ro.triviaapp.R
import com.yz3ro.triviaapp.presentation.onboarding.OnboardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(pages: List<OnboardingPage>, onFinish: () -> Unit) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.onboarding_active_dot_color))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(y = 50.dp, x = (-50).dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background_shape),
                    contentDescription = ""
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(y = (-150).dp, x = (30).dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background_shape_2),
                    contentDescription = ""
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                // Skip Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 12.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = onFinish,
                        border = BorderStroke(
                            1.dp,
                            colorResource(id = R.color.onboarding_bg_color)
                        ),
                        modifier = Modifier.size(80.dp, 35.dp)
                    ) {
                        Text(
                            text = "Atla",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                // Page Content
                HorizontalPager(
                    count = pages.size,
                    state = pagerState,
                    modifier = Modifier.weight(1f)
                ) { page ->
                    val pageContent = pages[page]
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(all = 20.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = pageContent.image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(300.dp)
                                .align(alignment = Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = pageContent.title,
                            fontSize = 22.sp,
                            style = TextStyle(
                                color = Color.Black
                            ),
                            fontFamily = Font(R.font.baloo2_semibold).toFontFamily()
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            modifier = Modifier.padding(all = 2.dp),
                            text = pageContent.description,
                            style = TextStyle(
                                color = Color.Black
                            ),
                            fontSize = 14.sp,
                            fontFamily = Font(R.font.inter_regular).toFontFamily()
                        )
                    }
                }
                // Page Indicator
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(12.dp),
                    activeColor = colorResource(id = R.color.onboarding_active_dot_color),
                    inactiveColor = colorResource(id = R.color.onboarding_inactive_dot_color)
                )
                // Navigation Button
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
                            Icon(
                                tint = colorResource(id = R.color.brush2),
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.width(48.dp))
                    }

                    if (pagerState.currentPage < pages.size - 1) {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                if (pagerState.currentPage < pages.size - 1) {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            }
                        }) {
                            Icon(
                                tint = Color.Black,
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null
                            )
                        }
                    } else {
                        OutlinedButton(
                            onClick = onFinish,
                            border = BorderStroke(1.dp, Color.Black),
                        ) {
                            Text(
                                text = "Başla",
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}
