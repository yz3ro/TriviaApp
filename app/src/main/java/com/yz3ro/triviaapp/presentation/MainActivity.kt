package com.yz3ro.triviaapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yz3ro.triviaapp.R
import com.yz3ro.triviaapp.presentation.feed.views.FeedScreen
import com.yz3ro.triviaapp.presentation.onboarding.OnboardingPage
import com.yz3ro.triviaapp.presentation.onboarding.views.OnboardingScreen
import com.yz3ro.triviaapp.presentation.signin.views.SignInScreen
import com.yz3ro.triviaapp.presentation.signup.views.SignUpScreen
import com.yz3ro.triviaapp.presentation.theme.TriviaAppTheme
import com.yz3ro.triviaapp.presentation.userdata.views.UserDataScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TriviaAppTheme {
                val systemUiController = rememberSystemUiController()
                val navController = rememberNavController()
                systemUiController.isStatusBarVisible = false
                NavHost(navController = navController, startDestination = Screen.OnBoardingScreen.route) {
                    composable(Screen.OnBoardingScreen.route) {
                        OnboardingScreen(
                            pages = listOf(
                                OnboardingPage(
                                    image = R.drawable.intro_1,
                                    title = "Trivia Arenasına Hoş Geldin!",
                                    description = "Bilginin rock yıldızı olmaya hazır mısın? En çılgın sorular, en havalı kategoriler ve sürprizlerle dolu bir serüven seni bekliyor! Bilgini konuştur, arkadaşlarını alt et ve trivia şampiyonu ol! Bu eğlence dolu meydan okumaya katıl ve bilgi dünyasının kahramanı ol! Hadi, başlıyoruz!"
                                ),
                                OnboardingPage(
                                    image = R.drawable.intro_2,
                                    title = "Nasıl oynanır?",
                                    description = "1. Kategori Seçimi: Favori kategorini seç! Spor, tarih, bilim, pop kültürü ve daha fazlası seni bekliyor.\n" +
                                            "2. Sorular ve Zaman: Her soru için belirli bir süren var. Düşün ve hızlıca cevapla!\n" +
                                            "3. Puanlama: Doğru cevaplar puan kazandırır, yanlış cevaplar ise puan kaybettirir.\n" +
                                            "4. Rekabet: Liderlik tablosunda en üstte yer almak için mücadele et!"
                                ),
                                OnboardingPage(
                                    image = R.drawable.intro_3,
                                    title = "İpuçları ve Püf Noktaları",
                                    description = "1. Odaklan ve Dikkatini Ver: Soruları dikkatlice oku ve en mantıklı cevabı seç.\n" +
                                            "2. Zamanı Yönet: Süreyi iyi kullan. Hızlı ama doğru cevaplar vermek önemli.\n" +
                                            "3. Araştır ve Öğren: Oyun dışında da yeni bilgiler edinerek kendini geliştir.\n" +
                                            "4. Strateji Geliştir: Güçlü olduğun kategorilere ağırlık ver ve zorlandığın alanlarda daha çok pratik yap."
                                )
                            )
                        ) {
                            navController.navigate(Screen.SignUpScreen.route) {
                                popUpTo(Screen.OnBoardingScreen.route) { inclusive = true }
                            }
                        }
                    }
                    composable(Screen.SignUpScreen.route) {
                        SignUpScreen(navController)
                    }
                    composable(Screen.SignInScreen.route) {
                        SignInScreen(navController)
                    }
                    composable(Screen.FeedScreen.route) {
                        FeedScreen()
                    }
                    composable(Screen.UserDataScreen.route) {
                        UserDataScreen()
                    }
                }
            }
        }
    }
}