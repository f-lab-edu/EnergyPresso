package com.eins.energypresso.ui.point

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.entity.cafe.find.value.CafeName
import com.eins.domain.entity.log.value.ChargeDate
import com.eins.domain.entity.log.value.ChargePoint
import com.eins.domain.entity.log.value.UseEndDate
import com.eins.domain.entity.log.value.UseStartDate
import com.eins.domain.entity.log.value.UseWatt
import com.eins.energypresso.R
import java.time.LocalDateTime

@Composable
fun PointLogScreen(
    modifier: Modifier = Modifier,
    chargeLogList: List<ChargeUseLog>,
    onClose: () -> Unit,
    onSelectItem: (ChargeUseLog) -> Unit
){
    Box(modifier = modifier){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                color = colorResource(
                    id = R.color.primary_color_3
                )
            )
        )

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row {
                ElevatedButton(
                    modifier = Modifier,
                    onClick = onClose
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "")
                }
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))

            Text(text = "충전 및 사용 기록", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(top = 5.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(chargeLogList.size){
                    when(val item = chargeLogList[it]){
                        is ChargeUseLog.ChargeLog -> ChargeLogItem(chargeLog = item)
                        is ChargeUseLog.UseLog -> UseLogItem(useLog = item)
                    }
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                }
            }
        }
    }
}

@Composable
private fun ChargeLogItem(
    chargeLog: ChargeUseLog.ChargeLog
){
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 20.dp),
                    text = "충전",
                    fontWeight = FontWeight.Bold,
                    color = Color.Green
                )
                Text(
                    text = "${chargeLog.chargePoint.get()}kWh",
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row {
                Text(text = chargeLog.chargeDate.getString(), fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            }
        }
    }
}


@Composable
private fun UseLogItem(
    useLog: ChargeUseLog.UseLog
){
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 20.dp),
                    text = "사용",
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = "${useLog.useWatt.get()}kWh",
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = useLog.cafeName.get(),
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row {
                Text(
                    text = useLog.useStartDate.getString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.padding(start = 5.dp))
                Text(
                    text = "~",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.padding(start = 5.dp))
                Text(
                    text = useLog.useEndDate.getString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
private fun previewUseLogItem(){
    UseLogItem(ChargeUseLog.UseLog(
        cafeName = CafeName("POLKI"),
        useWatt = UseWatt(1000),
        useStartDate = UseStartDate(LocalDateTime.now()),
        useEndDate = UseEndDate(LocalDateTime.now())
    ))
}

@Preview(apiLevel = 33)
@Composable
private fun previewChargeLogItem(){
    ChargeLogItem(ChargeUseLog.ChargeLog(
        chargePoint = ChargePoint(1000),
        chargeDate = ChargeDate(LocalDateTime.now())
    ))
}

@Preview
@Composable
private fun previewPointLogScreen(){
    PointLogScreen(
        chargeLogList = arrayListOf<ChargeUseLog>().apply {
            for (i in 0 .. 10){
                if(i % 2 == 0){
                    this.add(ChargeUseLog.ChargeLog(
                        chargePoint = ChargePoint(100 * i),
                        chargeDate = ChargeDate(LocalDateTime.now())
                    )
                    )
                }else{
                    this.add(ChargeUseLog.UseLog(
                        cafeName = CafeName("POLKI"),
                        useWatt = UseWatt(100 * i),
                        useStartDate = UseStartDate(LocalDateTime.now()),
                        useEndDate = UseEndDate(LocalDateTime.now())
                    ))
                }
            }
        },
        onClose = {

        }
    ){

    }
}