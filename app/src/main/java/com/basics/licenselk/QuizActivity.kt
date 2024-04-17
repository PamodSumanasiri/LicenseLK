package com.basics.licenselk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.basics.licenselk.data.Quiz
import com.basics.licenselk.data.QuizItems
import com.basics.licenselk.ui.theme.LicenseLKTheme

class QuizActivity : ComponentActivity() {

    private val viewModel : LicenseLKViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val quizCategory = intent.getParcelableExtra<Quiz>("quizCategory")
        Log.d("quizCategory", "quizCategory invoked")
        if (quizCategory != null) {
            viewModel.getPaperCategory(quizCategory)
            viewModel.initializeRandomSet()
        }

        setContent {
            LicenseLKTheme {

                // A surface container using the 'background' color from the theme
                Surface {
                    QuizScreenNew(
                        viewModel = viewModel,
                        navigateBack = { navigateBackToMainActivity() },
                    )
                }
            }
        }
        //viewModel.getRandomSet()
    }
    private fun navigateBackToMainActivity() {
        val intent = Intent(this@QuizActivity, MainActivity::class.java)
        startActivity(intent)
        finish() // Optionally, finish the current activity
    }

}



@Composable
fun QuizScreenNew(viewModel : LicenseLKViewModel,navigateBack :()-> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    QuizQuestionScreenNew(
        viewModel = viewModel,
        onSelectionChanged = { viewModel.onRadioButtonClicked(it) },
        onNextButtonClicked = { viewModel.onNextButtonClicked() },
        navigateBack = navigateBack,
        playAgain = { viewModel.quizList?.let { viewModel.resetQuizState(it) } },
        onBackButtonClicked = { viewModel.onQuizBackButtonClicked() }
    )
}


//@Composable
//fun QuizScreen(viewModel : LicenseLKViewModel,navigateBack :()-> Unit) {
//    val uiState by viewModel.uiState.collectAsState()
//    QuizQuestionScreen(
//        onSubmitButtonClicked = { viewModel.onSubmitButtonClicked() },
//        onBackButtonClicked = { viewModel.onQuizBackButtonClicked() },
//        currentScore = uiState.currentScore,
//        currentCount = uiState.currentCount,
//        viewModel = viewModel,
//        quizItems = uiState.currentSelectedQuizItems,
//        navigateBack = navigateBack
//    )
//}

