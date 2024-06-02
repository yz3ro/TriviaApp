import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yz3ro.triviaapp.R

@Composable
fun LeaderBoardScreen() {
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
            Text(text = "Leaderboard", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.categories_podium_bg),
                        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                    )
                    .weight(0.4f)
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)

                        ) {
                            androidx.compose.material.Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .background(
                                    colorResource(id = R.color.categories_podium_two_three_bg),
                                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                                )
                        ){
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(5.dp),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "2", fontSize = 16.sp)
                                Text(text = "Username" , fontSize = 12.sp)
                                TextButton(onClick = {  }, colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White
                                )) {
                                    Text(text = "0000", color = Color.Black)
                                }
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(4f)

                        ) {
                            androidx.compose.material.Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(6f)
                                .background(
                                    colorResource(id = R.color.categories_podium_one_bg),
                                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                                )
                        ){
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(5.dp),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(painter = painterResource(id = R.drawable.dollar), contentDescription ="" , modifier = Modifier.size(32.dp))
                                Text(text = "Username" , fontSize = 12.sp)
                                TextButton(onClick = {  }, colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White
                                )) {
                                    Text(text = "0000", color = Color.Black)
                                }
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)

                        ) {
                            androidx.compose.material.Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .background(
                                    colorResource(id = R.color.categories_podium_two_three_bg),
                                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                                )
                        ){
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(5.dp),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "3", fontSize = 16.sp)
                                Text(text = "Username" , fontSize = 12.sp)
                                TextButton(onClick = {  }, colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White
                                )) {
                                    Text(text = "0000", color = Color.Black)
                                }
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.categories_rank_bg),
                        shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                    )
                    .weight(0.6f)
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                OtherUsers()
            }
        }
    }
}
@Composable
fun OtherUsers() {
    LazyColumn(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(17) { index ->
            UserListItem(index + 4)
        }
    }
}

@Composable
fun UserListItem(index: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$index. Username",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "score: 0000",
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
    }
}