package com.yz3ro.triviaapp.presentation.feed.views.feedscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.yz3ro.triviaapp.R
import com.yz3ro.triviaapp.domain.model.Category

@Composable
fun CategoriesScreen() {
    val categories = listOf(
        Category("Genel Kültür", R.drawable.ic_music),
        Category("Spor", R.drawable.ic_music),
        Category("Bilim", R.drawable.ic_music),
        Category("Müzik", R.drawable.ic_music),
        Category("Sanat ve Edebiyat", R.drawable.ic_music),
        Category("Sözler ve Deyimler", R.drawable.ic_music),
        Category("Yemek ve İçecek", R.drawable.ic_music),
        Category("Doğa ve Hayvanlar", R.drawable.ic_music),
        Category("Eğlence ve Pop Kültür", R.drawable.ic_music),
        Category("Sinema ve TV", R.drawable.ic_music),
    )
    val colors = listOf(
        Color(0xFF3498DB),
        Color(0xFFE74C3C),
        Color(0xFF95A5A6),
        Color(0xFFF1C40F),
        Color(0xFFE67E22),
        Color(0xFF2C3E50),
        Color(0xFFD35400),
        Color(0xFF27AE60),
        Color(0xFFFF6F61),
        Color(0xFF9B59B6),
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
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "Categories", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)

        }
        LazyColumn {
            items(categories.size) { index ->
                val category = categories[index]
                val backgroundColor = colors[index % colors.size]  // Renkleri döngüsel olarak atayın
                CategoryCard(category, backgroundColor)
            }
        }
    }
}
@Composable
fun CategoryCard(category: Category, backgroundColor: Color) {
    androidx.compose.material.Card(
        modifier = Modifier
            .padding(all = 15.dp)
            .fillMaxWidth()
            .height(100.dp),
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = category.name, fontSize = 20.sp)
            Icon(
                painter = painterResource(id = category.icon),
                contentDescription = category.name,
                modifier = Modifier.size(60.dp),
                tint = Color.Unspecified
            )
        }
    }
}