package com.eins.energypresso.ui.router

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eins.energypresso.ui.drawer.NavigationDrawer
import com.eins.energypresso.ui.screen.MainScreen
import com.eins.energypresso.ui.screen.PreviewMainScreen
import com.eins.energypresso.ui.screen.UsePlugScreen
import com.eins.energypresso.ui.viewmodel.VisitedCafeListViewModel
import kotlinx.coroutines.launch

enum class MainScreenNavEnum{
    Main,
    Use,
    Coupon,
    Charge,
    Find,
    VisitedCafe
}

@Composable
fun MainRouter(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    viewModel: VisitedCafeListViewModel = hiltViewModel(),
) {
    val cafeListData by viewModel.cafeListData.collectAsState()
    viewModel.getVisitCafeList()

    val scope = rememberCoroutineScope()
    val userNickName = remember {
        mutableStateOf("")
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawer(
                    nickname = userNickName.value,
                    onClickMenu = {

                    }
                )
            }
        },
    ){
        NavHost(
            navController = rememberNavController(),
            startDestination = MainScreenNavEnum.Main.name
        ){
            composable(route = MainScreenNavEnum.Main.name){
                MainScreen(
                    visitCafeList = cafeListData,
                    onClickSubMenu = {},
                    onClickVisitedCafe = {},
                    onClickSideMenu = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            }

            composable(route = MainScreenNavEnum.Find.name){
                UsePlugScreen()
            }
        }
    }
}

@Preview
@Composable
fun previewMainScreen(){
    ModalNavigationDrawer(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        drawerContent = {
            ModalDrawerSheet { /* Drawer content */ }
        },
    ){
        PreviewMainScreen()
    }

}