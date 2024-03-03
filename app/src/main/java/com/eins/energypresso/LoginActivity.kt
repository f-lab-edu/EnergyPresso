package com.eins.energypresso

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.navercorp.nid.NaverIdLoginSDK
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eins.energypresso.ui.screen.LoginScreen
import com.eins.energypresso.ui.screen.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NaverIdLoginSDK.initialize(this,
            BuildConfig.NAVER_CLIENT_ID,
            BuildConfig.NAVER_CLIENT_SECRET,
            BuildConfig.NAVER_CLIENT_NAME
        )

        NaverIdLoginSDK.logout()


        setContent {
            EnergyPressoApp()
        }
    }
}

enum class InitialScreen() {
    Login,
    Main,
}

@Composable
fun EnergyPressoApp(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = InitialScreen.Login.name,
        modifier = Modifier){
        composable(route = InitialScreen.Login.name) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(InitialScreen.Main.name){
                    popUpTo(InitialScreen.Login.name) {
                        inclusive = true
                    }
                }
            })
        }

        composable(route = InitialScreen.Main.name){
            MainScreen()
        }
    }
}

@Composable
fun IdTextField(label: String, textChange: (String) -> Unit){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            textChange(text) },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
    )
}