package com.eins.energypresso.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eins.energypresso.IdTextField
import com.eins.energypresso.LoginViewModel
import com.eins.energypresso.R

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
){
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        val loginData by viewModel.loginData.collectAsState()
        loginData?.let {
            onLoginSuccess()
        }

        LoginScreen(onLoginTry = { id, pass ->
            viewModel.login(id, pass)
        })
    }
}

@Composable
private fun LoginScreen(
    onLoginTry: (String, String) -> Unit
){
    val idString = remember {
        mutableStateOf("")
    }

    val passString = remember {
        mutableStateOf("")
    }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = colorResource(id = R.color.secondary_color_3))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(all = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Energy Presso",
                    modifier = Modifier,
                    fontSize = 24.sp,
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Column(modifier = Modifier.padding(8.dp)) {
            IdTextField("아이디를 입력해주세요"){
                idString.value = it
            }

            IdTextField("비밀번호를 입력해주세요"){
                passString.value = it
            }

            Button(
                onClick = {
                    onLoginTry(idString.value, passString.value)
                },
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "로그인 하기", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen(onLoginTry = { id, pass ->

    })
}