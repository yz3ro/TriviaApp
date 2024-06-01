package com.yz3ro.triviaapp.presentation.userdata.views.userdatascreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yz3ro.triviaapp.R

@Composable
fun StartButtonPage() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(350.dp, 420.dp)
                .padding(all = 20.dp)
                .background(
                    colorResource(id = R.color.userdata_box),
                    shape = RoundedCornerShape(16.dp)
                ), contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.trophy),
                    contentDescription = "",
                    modifier = Modifier.size(74.dp, 74.dp),
                )
                Text(text = "Hadi Başlayalım!", color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Her şey hazır! Şimdi bilgi dünyasında rakiplerini alt etmek için harekete geçme zamanı.\n" +
                            " En sevdiğin kategoriyi seç, soruları cevapla ve liderlik tablosunda zirveye tırman.\n" +
                            " Eğlence başlasın!",
                    color = Color.White,
                )
            }
        }

    }

}