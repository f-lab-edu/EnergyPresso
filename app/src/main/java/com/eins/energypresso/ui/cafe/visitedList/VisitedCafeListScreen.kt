package com.eins.energypresso.ui.cafe.visitedList

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eins.domain.entity.UseTime
import com.eins.domain.entity.VisitedCafe
import com.eins.energypresso.ui.screen.RecentVisitCafeScreen

@Composable
fun VisitedCafeListScreen(
    modifier: Modifier,
    cafeList: List<VisitedCafe>,
    onClickVisitedCafe: (VisitedCafe) -> Unit
) {
    Text(text = "최근 방문한 카페", modifier = modifier)
    LazyColumn(modifier = Modifier.height(300.dp)){
        RecentVisitCafeScreen(
            visitCafe = cafeList,
            onItemClick = {
                onClickVisitedCafe(cafeList[it])
            }
        )
    }
}

@Preview
@Composable
private fun PreviewVisitedCafeListScreen(){
    VisitedCafeListScreen(
        modifier = Modifier,
        cafeList = arrayListOf<VisitedCafe>().apply {
        for(i in 1 .. 10){
            this.add(VisitedCafe(
                cafeName = "카페 A-$i",
                address = "주소",
                useTime = UseTime(
                    hour = 10,
                    min = 30
                ),
                useWatt = 500
            ))
        }
    }, onClickVisitedCafe = { index ->

    })
}