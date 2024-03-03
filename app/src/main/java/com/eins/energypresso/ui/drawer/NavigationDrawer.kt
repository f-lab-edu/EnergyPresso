package com.eins.energypresso.ui.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class SideMenuEnum(val menuName: String){
    UseChargeLog("충전 및 사용 기록")
}

@Composable
fun NavigationDrawer(
    nickname: String,
    onClickMenu: (SideMenuEnum) -> Unit
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = Icons.Filled.SupervisedUserCircle,
                    modifier = Modifier.height(100.dp).width(100.dp),
                    contentDescription = "Icons.Filled.Architecture")
                Text(text = nickname)
            }
        }
    ) { it ->
        Surface(modifier = Modifier.padding(it)) {
            Column {
                SideMenuEnum.entries.forEach { sideMenu ->
                    SideMenuItem(sideMenu){
                        onClickMenu(sideMenu)
                    }
                }
            }
        }
    }
}

@Composable
fun SideMenuItem(
    sideMenuEnum: SideMenuEnum,
    onClickMenu: () -> Unit
){
    Row(
        modifier = Modifier.padding(10.dp).clickable { onClickMenu() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Filled.FormatListNumbered,
            contentDescription = "Icons.Filled.Architecture",
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
        )
        Text(
            text = sideMenuEnum.menuName,
            modifier = Modifier.weight(1f).padding(start = 10.dp)
        )
        ElevatedButton(onClick = {

        }) {
            Image(
                imageVector = Icons.AutoMirrored.Filled.NavigateNext,
                contentDescription = "Icons.Filled.Architecture",
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewNavigationDrawer(){
    NavigationDrawer("사용자"){

    }
}