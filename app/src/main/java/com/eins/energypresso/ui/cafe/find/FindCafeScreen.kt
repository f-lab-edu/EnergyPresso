package com.eins.energypresso.ui.cafe.find

import androidx.cardview.widget.CardView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import coil.compose.AsyncImage
import com.eins.domain.entity.cafe.find.AroundCafeData
import com.eins.domain.entity.cafe.find.FrequentlyCafeData
import com.eins.domain.entity.cafe.find.value.Address
import com.eins.domain.entity.cafe.find.value.CafeName
import com.eins.domain.entity.cafe.find.value.ImageUrl
import com.eins.domain.entity.cafe.find.value.Star
import com.eins.energypresso.R

@Composable
fun FindCafeScreen(
    aroundCafeList: List<AroundCafeData>,
    frequentlyCafeList: List<FrequentlyCafeData>,
    onClose: () -> Unit
){
    Column(modifier = Modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.primary_color_2)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                modifier = Modifier,
                text = "카페 찾기",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text(text = "자주 방문한 카페")
            }

            Spacer(modifier = Modifier.padding(5.dp))
            LazyRow(modifier = Modifier.padding(start = 20.dp, end = 20.dp)){
                items(frequentlyCafeList.size){
                    FrequentlyCafe(
                        imageUrl = frequentlyCafeList[it].imageUrl.get(),
                        cafeName = frequentlyCafeList[it].cafeName.get()
                    )
                    Spacer(modifier = Modifier.padding(start = 10.dp))
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))
        }

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "근처 카페 찾기")
            Spacer(modifier = Modifier.padding(top = 20.dp))
            LazyColumn(modifier = Modifier) {
                items(aroundCafeList.size){
                    AroundCafe(
                        imageUrl = aroundCafeList[it].imageUrl.get(),
                        cafeName = aroundCafeList[it].cafeName.get(),
                        star = aroundCafeList[it].star.get(),
                        address = aroundCafeList[it].address.get())
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                }
            }
        }
    }
    Box(modifier = Modifier.padding(start = 20.dp, top = 5.dp)) {
        ElevatedButton(
            modifier = Modifier,
            onClick = onClose
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "")
        }
    }
}


@Composable
private fun FrequentlyCafe(
    imageUrl: String,
    cafeName: String
){
    Column(
        modifier = Modifier,
    ) {
        ElevatedCard {
            AsyncImage(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp),
                model = imageUrl,
                contentDescription = "Translated description of what the image contains"
            )
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Text(text = cafeName)
    }
}

@Composable
private fun AroundCafe(
    imageUrl: String,
    cafeName: String,
    star: Float,
    address: String
){
    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(
            containerColor = colorResource(id = R.color.primary_color_1)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp),
                model = imageUrl,
                contentDescription = "Translated description of what the image contains"
            )
            Spacer(modifier = Modifier.padding(start = 10.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Text(text = cafeName)
                Text(text = "평점 : $star")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = address)
            }
        }
    }
}


@Preview(apiLevel = 33)
@Composable
private fun PreviewAroundCafe(){
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (i in 0 .. 10){
            item{
                AroundCafe(
                    imageUrl = "https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200506_223%2F1588730227110kCxsl_JPEG%2FuI6citkjAfP1NRbklRuQvpBP.jpeg.jpg",
                    cafeName = "FOLKI",
                    star = 4.5f,
                    address = "서울 종로구 사직로9길 6")
                Spacer(modifier = Modifier.padding(top = 10.dp))
            }
        }
    }
}

@Preview(apiLevel = 33)
@Composable
private fun PreviewFrequentlyCafe(){
    LazyRow(
        modifier = Modifier.height(100.dp)
    ) {
        for(i in 0 .. 10){
            item {
                FrequentlyCafe(
                    imageUrl = "https://jinitrip.com/wp-content/uploads/2017/06/cafe-datecourse-2.jpg",
                    cafeName = "서촌 카페"
                )
            }
        }
    }
}

@Preview(apiLevel = 33)
@Composable
private fun Preview(){
    FindCafeScreen(
        frequentlyCafeList = arrayListOf<FrequentlyCafeData>().apply {
            for(i in 0 .. 10){
                this.add(FrequentlyCafeData(
                    imageUrl = ImageUrl("https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200506_223%2F1588730227110kCxsl_JPEG%2FuI6citkjAfP1NRbklRuQvpBP.jpeg.jpg"),
                    cafeName = CafeName("FOLKI")
                ))
            }
        },
        aroundCafeList = arrayListOf<AroundCafeData>().apply {
            for (i in 0 .. 10){
                this.add(AroundCafeData(
                    imageUrl = ImageUrl("https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200506_223%2F1588730227110kCxsl_JPEG%2FuI6citkjAfP1NRbklRuQvpBP.jpeg.jpg"),
                    cafeName = CafeName("FOLKI"),
                    star = Star(4.5f),
                    address = Address("서울 종로구 사직로9길 6")
                ))
            }
        },
        onClose = {

        }
    )
}