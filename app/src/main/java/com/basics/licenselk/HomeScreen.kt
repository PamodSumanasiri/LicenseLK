package com.basics.licenselk

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.basics.licenselk.data.ScreenType
import com.basics.licenselk.data.Tip

@Composable
fun HomeScreen(
    licenseLKUiState: LicenseLKUiState,
    onCardClick : (Tip) -> Unit,
    onBackButtonClicked : () -> Unit,
    modifier: Modifier = Modifier
){

    if (licenseLKUiState.isShowingHomePage){
        AppContent(
            onCardClick = onCardClick,
            //modifier = modifier
        )
    }else{
        CardDetailsPage(
            licenseLKUiState = licenseLKUiState,
            onBackButtonClicked = onBackButtonClicked,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.bottom_bar_padding_bottom))
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppContent(
    onCardClick : (Tip) -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inverseOnSurface)
    ) {
                CardList(
                    onCardClick = onCardClick,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.bottom_bar_padding_bottom))
                )
    }
}






@Composable
fun BottomNavigationBar(
    //currentTab : ScreenType,
    onTabPressed : ((NavigationItemContent) -> Unit),
    navigationItemContentList : List<NavigationItemContent>,
    navController: NavController,
    modifier: Modifier = Modifier
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(modifier = modifier) {
        navigationItemContentList.forEach{
            val selected = it.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = MaterialTheme.colorScheme.onPrimary
//                ),
                onClick = { onTabPressed(it) },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.text
                    )
                }
            )
        }
    }
}


data class NavigationItemContent(
    val screenType: ScreenType,
    val icon : ImageVector,
    val text : String,
    val route : String
)