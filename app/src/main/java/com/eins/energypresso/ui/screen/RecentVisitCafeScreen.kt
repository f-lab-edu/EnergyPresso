package com.eins.energypresso.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eins.domain.entity.UseTime
import com.eins.domain.entity.VisitedCafe

@Composable
fun RecentVisitCafeScreen(
    modifier: Modifier,
    visitCafe: List<VisitedCafe>,
    onItemClick: (Int) -> Unit
){
    Scaffold(
        topBar = {
            Text(text = "최근 이용한 카페", fontWeight = FontWeight.Bold)
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            visitCafe.forEachIndexed { index, visitedCafe ->
                VisitCafeItem(visitedCafe){
                    onItemClick(index)
                }
            }
        }
    }
}

@Composable
private fun VisitCafeItem(
    visitCafe: VisitedCafe,
    onItemClick: () -> Unit
){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        onClick = onItemClick
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(text = visitCafe.cafeName, fontWeight = FontWeight.Bold)
            Text(text = visitCafe.address, fontWeight = FontWeight.Bold, color = Color.Gray)
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "${visitCafe.useTime.toString()} 이용, ${visitCafe.useWatt.toString()}kWh",
                fontWeight = FontWeight.Bold, color = Color.Gray)
        }
    }
}

@Preview
@Composable
private fun ReviewVisitCafeItem(){
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

    RecentVisitCafeScreen(Modifier, list){ onClickItemIdx ->

    }
}