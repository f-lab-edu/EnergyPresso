package com.eins.energypresso.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eins.domain.entity.UseTime
import com.eins.domain.entity.VisitedCafe

@Composable
fun MainScreen(

) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "에너지 프레소",
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Button(
                        onClick = { TODO() },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text("메뉴")
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding).padding(10.dp)
            .fillMaxWidth()) {
            UsableWattScreen(currentCharge = 200, onClickSubMenu = {

            })
            val list = arrayListOf<VisitedCafe>(
                VisitedCafe(
                    cafeName = "카페 A",
                    address = "A시 B구 C동 123-1",
                    useTime = UseTime(
                        hour = 10,
                        min = 30
                    ),
                    useWatt = 1000
                ),
                VisitedCafe(
                    cafeName = "카페 B",
                    address = "A시 B구 C동 123-2",
                    useTime = UseTime(
                        hour = 10,
                        min = 30
                    ),
                    useWatt = 1000
                ),
                VisitedCafe(
                    cafeName = "카페 C",
                    address = "A시 B구 C동 123-3",
                    useTime = UseTime(
                        hour = 10,
                        min = 30
                    ),
                    useWatt = 1000
                ),
            )

            RecentVisitCafeScreen(
                modifier = Modifier.padding(top = 20.dp),
                visitCafe = list,
                onItemClick = {}
            )
        }
    }
}

@Preview
@Composable
fun previewMainScreen(){
    MainScreen()
}