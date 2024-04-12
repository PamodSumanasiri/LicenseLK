package com.basics.licenselk

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.basics.licenselk.data.Quiz
import com.basics.licenselk.data.Tip

@Composable
fun AppNavigationControl(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    context: Context
    ) {

    val viewModel : LicenseLKViewModel = viewModel()
    val licenseLKUiState = viewModel.uiState.collectAsState().value

    NavHost(navController = navController, startDestination = "Tips"){
        composable("Tips"){
            HomeScreen(
                licenseLKUiState = licenseLKUiState,
                onCardClick = {tip: Tip ->
                    viewModel.updateDetailScreenState(tip)
                },
                onBackButtonClicked = { viewModel.resetHomeScreenState() }
            )
        }
        composable("Theories"){
            TheoryScreen(
                licenseLKUiState = licenseLKUiState,
                onclick = {
                          viewModel.updateTheoryScreenState(it)
                },
                onBackButtonClicked = { viewModel.resetTheoryScreenState() }
            )
        }
        composable("Quiz"){
            QuizCategoryScreen(
                context = context,
                onclick = {
                    viewModel.updateQuizScreenState(it)}
            )  //Pass the actual later
        }
        composable("For You"){
            ForYouScreen()
        }
    }

}





@Composable
fun ForYouScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Motivation")
    }
}