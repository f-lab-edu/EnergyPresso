package com.eins.energypresso.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eins.energypresso.R

enum class SubMenuEnum(val imageResId: Int, val text: String){
    UsePlug(R.drawable.qr_img, "플러그 사용하기"),
    Coupon(R.drawable.coupon_img, "쿠폰 등록하기"),
    Charge(R.drawable.won_img, "요금 충전하기")
}

@Composable
fun UsableWattScreen(
    currentCharge: Int,
    onClickSubMenu: (SubMenuEnum) -> Unit
){
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "사용할 수 있는 전기량", fontWeight = FontWeight.Bold, fontSize = 10.sp)
            Column(modifier = Modifier
                .padding(top = 10.dp)) {
                Text(
                    text = "$currentCharge kWh",
                    color = Color.Green,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SubMenuEnum.entries.forEach {
                    MenuButton(it){
                        onClickSubMenu(it)
                    }
                }
            }
        }
    }
}

@Composable
private fun MenuButton(
    subMenuEnum: SubMenuEnum,
    onClickSubMenu: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onClickSubMenu()
        }
    ) {
        Image(
            painter = painterResource(
                id = subMenuEnum.imageResId
            ),
            contentDescription = "subMenuEnum.imageResId"
        )
        Text(text = subMenuEnum.text, modifier = Modifier.padding(top = 2.dp), fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
private fun PreviewUsableWattScreen(){
    UsableWattScreen(100){

    }
}