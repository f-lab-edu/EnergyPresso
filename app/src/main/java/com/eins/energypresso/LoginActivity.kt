package com.eins.energypresso

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {
    private val viewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NaverIdLoginSDK.initialize(this,
            this.resources.getString(R.string.naver_client_id),
            this.resources.getString(R.string.naver_client_secret),
            this.resources.getString(R.string.naver_client_name)
        )

        NaverIdLoginSDK.logout()


        setContent {
            MyApp()
        }
    }

    @Composable
    fun MyApp(){
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            Column {
                TitleCard("Energy Presso")
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    IdTextField(label = "아이디를 입력해주세요"){
                        viewModel.idString.value = it
                    }

                    IdTextField(label = "비밀번호를 입력해주세요"){
                        viewModel.passString.value = it
                    }

                    ErrorBox()

                    LoginButton(modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()){

                    }

                    ThirdPartyLoginView(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp))
                }
            }
        }
    }

    @Composable
    fun ErrorBox(){
        val error by viewModel.error.collectAsState()
        error?.let { errorMessage ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = errorMessage, color = Color.Red)
            }
        }
    }

    @Composable
    fun TitleCard(name: String){
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
                    text = name,
                    modifier = Modifier,
                    fontSize = 24.sp,
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold
                )
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

    @Composable
    fun LoginButton(modifier: Modifier, onClick: () -> Unit){
        Button(
            onClick = {
                Log.d("LoginButton", "onClick")
            },
            modifier = modifier
        ) {
            Text(text = "로그인 하기", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }

    @Composable
    fun ThirdPartyLoginView(modifier: Modifier){
        val error = remember {
            mutableStateOf<String?>(null)
        }

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "소셜 계정으로 로그인하기")
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 20.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.naver_login),
                    modifier = Modifier.clickable {
                        NaverIdLoginSDK.authenticate(this@LoginActivity, object : OAuthLoginCallback {
                            override fun onError(errorCode: Int, message: String) {
                                error.value = "오류가 발생했습니다"
                            }

                            override fun onFailure(httpStatus: Int, message: String) {
                                error.value = "네이버 로그인에 실패했습니다"
                            }

                            override fun onSuccess() {
                                //error.value = "네이버 로그인에 성공했습니다"
                                NaverIdLoginSDK.getAccessToken()?.let {

                                } ?: kotlin.run {
                                    NaverIdLoginSDK.logout()
                                }
                            }
                        })
                    },
                    contentDescription = "네이버 로그인 이미지"
                )
            }
        }

        error.value?.let{
            AlertDialogExample(
                onDismissRequest = {
                    error.value = null
                },
                onConfirmation =  {
                    error.value = null
                },
                dialogTitle = "",
                dialogText = it,
                icon = Icons.Default.Info
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AlertDialogExample(
        onDismissRequest: () -> Unit,
        onConfirmation: () -> Unit,
        dialogTitle: String,
        dialogText: String,
        icon: ImageVector,
    ) {
        AlertDialog(
            icon = {
                Icon(icon, contentDescription = "Example Icon")
            },
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(text = dialogText)
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }

    //@Preview(name = "Light Mode", showBackground = true)
    @Preview(name = "Full Preview", showSystemUi = true)
    @Composable
    fun PreviewTitleCard(){
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            Column {
                TitleCard("Energy Presso")
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    IdTextField(label = "아이디를 입력해주세요"){
                        viewModel.idString.value = it
                    }

                    IdTextField(label = "비밀번호를 입력해주세요"){
                        viewModel.passString.value = it
                    }

                    //ErrorBox()

                    LoginButton(modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()){

                    }

                    ThirdPartyLoginView(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp))
                }
            }
        }
    }
}