package com.eins.energypresso.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eins.domain.entity.UseTime
import com.eins.domain.entity.VisitedCafe
import com.eins.energypresso.ui.cafe.visitedList.VisitedCafeListScreen
import com.eins.energypresso.ui.cafe.visitedList.VisitedCafeListViewModel


@Composable
fun MainScreen(
    visitedCafeList: List<VisitedCafe>,
    onSelectVisitedCafe: (VisitedCafe) -> Unit,
    onClickSubMenu: (SubMenuEnum) -> Unit,
    onClickSideMenu: () -> Unit,
    onClickFindCafe: () -> Unit
){
    Scaffold(
        topBar = {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "에너지 프레소"
                    )

                    Row {
                        Icon(
                            modifier = Modifier.clickable(onClick = onClickSideMenu),
                            imageVector = Icons.Default.Apps,
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    ) {
        LazyColumn(modifier = Modifier
            .padding(it)
            .padding(10.dp)
            .fillMaxWidth()
        ) {
            item{
                UsableWattScreen(currentCharge = 200, onClickSubMenu = {
                    onClickSubMenu(it)
                })
            }

            item {
                ElevatedButton(
                    onClick = onClickFindCafe,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .height(50.dp)
                ) {
                    Text(text = "충전 플러그 사용 가능한 카페 찾기")
                }
            }

            item{
                VisitedCafeListScreen(
                    modifier = Modifier,
                    cafeList = visitedCafeList,
                    onClickVisitedCafe = onSelectVisitedCafe
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen(){
    MainScreen(
        visitedCafeList = arrayListOf(),
        onSelectVisitedCafe = {

        },
        onClickSubMenu = {

        },
        onClickSideMenu = {

        },
        onClickFindCafe = {

        }
    )
}