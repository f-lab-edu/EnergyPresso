package com.eins.energypresso.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
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

enum class MainMenuList{
    SideMenu,
    UsePlugMenu
}

@Composable
fun MainScreen(
    viewModel: VisitedCafeListViewModel = hiltViewModel(),
    onClickSubMenu: (SubMenuEnum) -> Unit,
    onClickVisitedCafe: (VisitedCafe) -> Unit,
    onClickSideMenu: (MainMenuList) -> Unit,
){
    val cafeListData = viewModel.cafeListData.collectAsState()

    viewModel.getVisitCafeList()
    
    LazyColumn(modifier = Modifier
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
                onClick = { onClickSideMenu(MainMenuList.UsePlugMenu) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .height(50.dp)
            ) {
                Text(text = "충전 플러그 사용 가능한 카페 찾기")
            }
        }

        item{
            val vm: VisitedCafeListViewModel = hiltViewModel()

            val list = vm.cafeListData.collectAsState()

            VisitedCafeListScreen(
                modifier = Modifier,
                cafeList = list.value,
                onClickVisitedCafe = {

                }
            )
        }
    }

}

@Composable
private fun MainScreen(
    onClickSubMenu: (SubMenuEnum) -> Unit,
    onClickVisitedCafe: (VisitedCafe) -> Unit
){
    LazyColumn(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
    ) {
        item{
            UsableWattScreen(currentCharge = 200, onClickSubMenu = {
                onClickSubMenu(it)
            })
        }

        val list = arrayListOf<VisitedCafe>()

        for(i in 0 .. 10){
            list.add(
                VisitedCafe(
                    cafeName = "",
                    address = "",
                    useTime = UseTime(
                        hour = 0,
                        min = 0
                    ),
                    useWatt = 1000
                ))
        }

        item {
            ElevatedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .height(50.dp)
                ) {
                Text(text = "충전 플러그 사용 가능한 카페 찾기")
            }
        }

        item{
            Text(text = "최근 방문한 카페", modifier = Modifier.padding(top = 20.dp))
            LazyColumn(modifier = Modifier.height(300.dp)){
                RecentVisitCafeScreen(
                    visitCafe = list,
                    onItemClick = {
                        onClickVisitedCafe(list[it])
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen(){
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
        Surface(modifier = Modifier.padding(innerPadding)) {
            MainScreen(onClickSubMenu = {}, onClickVisitedCafe = {})
        }
    }
}