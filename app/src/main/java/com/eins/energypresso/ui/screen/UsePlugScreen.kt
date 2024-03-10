package com.eins.energypresso.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eins.energypresso.ui.viewmodel.UsableWattViewModel


@Composable
fun UsePlugScreen(
    viewModel: UsableWattViewModel = hiltViewModel()
){
    val currentProgress by remember {
        mutableFloatStateOf(0.5f)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimerProgressBox(
            currentProgress = currentProgress,
            timeString = "2시간 00분",
            sizeDp = 150.dp)

        ExplainCard(
            modifier = Modifier.padding(top = 30.dp),
            timeText = "14시 30분"
        )
        
        Row(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            ElevatedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f).padding(end = 5.dp),
            ) {
                Text(text = "시용해제")
            }

            ElevatedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f).padding(start = 5.dp)
            ) {
                Text(text = "시간 연장")
            }
        }
    }
}

@Composable
fun ExplainCard(
    modifier: Modifier = Modifier,
    timeText: String
){
    ElevatedCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                modifier = Modifier,
                text = "${timeText}에 전원이 차단됩니다",
                fontWeight = FontWeight.Bold
            )
            Text(modifier = Modifier.padding(top = 10.dp),
                fontSize = 8.sp,
                text = """
                전원이 차단되었을 때 파손 가능성이 있는 전자기기는
                전원이 차단되기 전에 안전하게 전원을 분리해주세요
            """.trimIndent())
        }
    }
}

@Composable
fun TimerProgressBox(
    currentProgress: Float,
    timeString: String,
    sizeDp: Dp
){
    Box(
        modifier = Modifier
            .width(sizeDp)
            .height(sizeDp),
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(sizeDp)
                .height(sizeDp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            progress = {currentProgress}
        )

        Column(
            modifier = Modifier
                .width(sizeDp)
                .height(sizeDp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = timeString, fontSize = 14.sp)
            Text(text = "남은 시간", fontSize = 8.sp)
        }
    }
}


@Preview(apiLevel = 33)
@Composable
private fun preview(){
    UsePlugScreen()
}