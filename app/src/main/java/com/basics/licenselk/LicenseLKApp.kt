package com.basics.licenselk

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.basics.licenselk.data.ScreenType
import com.basics.licenselk.data.Tip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicenseApp(
    modifier: Modifier = Modifier,
    viewModel: LicenseLKViewModel = viewModel() //new

){
    val uiState = viewModel.uiState.collectAsState().value  //new
    val navigationItemContentList : List<NavigationItemContent> = listOf(
        NavigationItemContent(
            screenType = ScreenType.Tip,
            icon = Icons.Default.Home,
            text = stringResource(id = R.string.tab_tip),
            route = stringResource(id = R.string.tab_tip)
        ),
        NavigationItemContent(
            screenType = ScreenType.Theory,
            icon = Icons.Default.ThumbUp,
            text = stringResource(id = R.string.tab_theory),
            route = stringResource(id = R.string.tab_theory)
        ),
        NavigationItemContent(
            screenType = ScreenType.Quiz,
            icon = Icons.Default.PlayArrow,
            text = stringResource(id = R.string.tab_quiz),
            route = stringResource(id = R.string.tab_quiz)
        ),
        NavigationItemContent(
            screenType = ScreenType.Motivation,
            icon = Icons.Default.Info,
            text = stringResource(id = R.string.tab_motivation),
            route = stringResource(id = R.string.tab_motivation)
        )
    )
    val navController : NavController = rememberNavController()
        Scaffold(
            bottomBar = {
                  //new
                    BottomNavigationBar(
                        onTabPressed = {
                            navController.navigate(it.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        },
                        navigationItemContentList = navigationItemContentList,
                        navController = navController
                    )

            }
        ) {
            AppNavigationControl(
                navController = navController as NavHostController,
                modifier = Modifier.padding(it),
                context = LocalContext.current
                )

        }

}
