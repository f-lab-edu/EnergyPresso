package com.eins.energypresso.ui.router

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eins.energypresso.MainActivityViewModel
import com.eins.energypresso.ui.cafe.find.FindCafeScreen
import com.eins.energypresso.ui.cafe.find.FindCafeScreenViewModel
import com.eins.energypresso.ui.drawer.NavigationDrawer
import com.eins.energypresso.ui.screen.MainMenuList
import com.eins.energypresso.ui.screen.MainScreen
import com.eins.energypresso.ui.screen.MainScreenViewModel
import com.eins.energypresso.ui.screen.PreviewMainScreen
import com.eins.energypresso.ui.screen.UsePlugScreen
import kotlinx.coroutines.launch

enum class MainScreenNavEnum{
    Main,
    Use,
    Coupon,
    Charge,
    FindCafe,
    VisitedCafe
}

@Composable
fun MainRouter(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {
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
        val mainNavHost = rememberNavController()
        NavHost(
            navController = mainNavHost,
            startDestination = MainScreenNavEnum.Main.name
        ){
            composable(route = MainScreenNavEnum.Main.name){
                val viewModel: MainScreenViewModel = hiltViewModel()

                MainScreen(
                    visitedCafeList = viewModel.cafeListData.collectAsState().value,
                    onSelectVisitedCafe = {

                    },
                    onClickSubMenu = {

                    },
                    onClickSideMenu = {

                    },
                    onClickFindCafe = {
                        mainNavHost.navigate(MainScreenNavEnum.FindCafe.name)
                    }
                )
            }

            composable(route = MainScreenNavEnum.Use.name){
                UsePlugScreen{
                    mainNavHost.navigate(MainScreenNavEnum.Main.name){
                        popUpTo(MainScreenNavEnum.Use.name)
                    }
                }
            }

            composable(route = MainScreenNavEnum.FindCafe.name){
                val viewModel: FindCafeScreenViewModel = hiltViewModel()
                FindCafeScreen(
                    aroundCafeList = viewModel.aroundCafeList.collectAsState().value,
                    frequentlyCafeList = viewModel.frequentlyCafeList.collectAsState().value,
                    onClose = {
                        mainNavHost.navigate(MainScreenNavEnum.Main.name)
                    }
                )
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