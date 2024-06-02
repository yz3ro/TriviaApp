package com.yz3ro.triviaapp.presentation.feed.views.feedscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yz3ro.triviaapp.R
import com.yz3ro.triviaapp.domain.model.Category
import com.yz3ro.triviaapp.presentation.feed.FeedViewModel

@Composable
fun HomeScreen() {
    val leaderboardList = remember { mutableStateListOf("yusuf", "betül", "elif", "arda", "mert") }
    val pointList = remember { mutableStateListOf("1298", "1187", "1078", "995", "456") }
    val orderList = remember { mutableStateListOf("1.", "2.", "3.") }
    val viewModel : FeedViewModel = hiltViewModel()
    val categories = listOf(
        Category("Genel Kültür", R.drawable.ic_general_culture),
        Category("Spor", R.drawable.ic_sports),
        Category("Bilim", R.drawable.ic_science),
        Category("Müzik", R.drawable.ic_music)
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Leaderboard", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "devamı..",
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    viewModel.setSelectedIndex(2)
                }
            )
        }
        LazyColumn {
            items(
                count = 3,
                itemContent = {
                    val leaderBoard = leaderboardList[it]
                    val point = pointList[it]
                    val order = orderList[it]
                    Card(
                        modifier = Modifier
                            .padding(all = 5.dp)
                            .size(332.dp, 54.dp)
                    ) {
                        Row {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(all = 10.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "$order $leaderBoard",
                                    modifier = Modifier.padding(all = 5.dp)
                                )
                                Text(
                                    text = "score : $point",
                                    modifier = Modifier.padding(all = 5.dp),
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Featured Categories",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = "devamı..",
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    viewModel.setSelectedIndex(1)
                }
            )
        }
        CategoriesGrid(categories = categories)
        Text(
            text = "Daily Quiz",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )
        QuizCard(questionCount = 5)
    }
}

@Composable
fun CategoriesGrid(categories: List<Category>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),  // 2 sütunlu bir ızgara
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories) { category ->
            CategoryItem(category = category)
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    androidx.compose.material.Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp),
        backgroundColor = Color.White,
        elevation = 4.dp,
        shape = RoundedCornerShape(36.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = category.icon),
                contentDescription = category.name,
                modifier = Modifier.size(40.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = category.name,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun QuizCard(questionCount: Int) {
    androidx.compose.material.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .height(100.dp),
        elevation = 4.dp,
        backgroundColor = Color(0xFFE1BEE7)
    ) {
        Box(contentAlignment = Alignment.CenterEnd) {
            Text(
                text = "$questionCount Soru",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}