package com.basics.licenselk

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basics.licenselk.data.QuizItems
import kotlinx.coroutines.delay


@Composable
fun QuizQuestionScreen(
    modifier: Modifier = Modifier,
//    onSelectionChanged: (String) -> Unit,
    onSubmitButtonClicked: () -> Unit,
    onBackButtonClicked : () -> Unit,
    currentScore : Int,
    currentCount : Int,
    viewModel : LicenseLKViewModel,
    quizItems: QuizItems,
    navigateBack: ()-> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.column_padding_horizontal))
    ) {
        QuizScreenTopBar(
            currentSelectedQuiz = quizItems,
            onBackButtonClicked = onBackButtonClicked
        )
        ImageAndScoreCount(
            image = quizItems.Image,
            currentScore = currentScore,
            currentCount = currentCount
        )
        OptionListAndSubmitButton(
            quizItems = quizItems,
            viewModel = viewModel,
            onSubmitButtonClicked = onSubmitButtonClicked
        )
        if (viewModel.correctAnswer == false){
            LaunchedEffect(uiState){
                delay(2000)
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)) {
                Text(
                    text = "Correct Answer is ${uiState.currentSelectedQuizItems.CorrectOption}",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        if (uiState.isGameOver){
            AlertDialog(score = currentScore, playAgain = { viewModel.onPlayAgainClicked() }, navigateBack = navigateBack)
        }
        if (uiState.leaveAlert){
            AlertDialog2(dismiss = { viewModel.onQuizDismissButtonClicked() }, navigateBack =  navigateBack )
        }


    }
}

@Composable
private fun AlertDialog(
    score: Int,
    playAgain: ()-> Unit,
    navigateBack: ()-> Unit,
    modifier: Modifier = Modifier
){

    androidx.compose.material3.AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text(text = stringResource(id = R.string.congratulation))},
        text = { Text(text = stringResource(id = R.string.your_score,score))},
        modifier = modifier,
        dismissButton = { TextButton(onClick = { navigateBack()  }) {
            Text(text = stringResource(id = R.string.back_to_menu))
        }
        },
        confirmButton = { TextButton(onClick = playAgain) {
            Text(text = stringResource(id = R.string.play_again))
        }
        }

    )
}

@Composable
private fun AlertDialog2(
    dismiss: ()-> Unit,
    navigateBack: ()-> Unit,
    modifier: Modifier = Modifier
){

    androidx.compose.material3.AlertDialog(
        onDismissRequest = dismiss ,
        title = { Text(text = stringResource(id = R.string.warning))},
        text = { Text(text = stringResource(id = R.string.leave_quiz))},
        modifier = modifier,
        dismissButton = { TextButton(onClick =  dismiss ) {
            Text(text = stringResource(id = R.string.no_option))
        }
        },
        confirmButton = { TextButton(onClick = navigateBack) {
            Text(text = stringResource(id = R.string.yes_option))
        }
        }

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionListAndSubmitButton(
    modifier: Modifier = Modifier,
    quizItems: QuizItems,
//    onSelectionChanged : (String) -> Unit,
    onSubmitButtonClicked : () -> Unit,
    viewModel: LicenseLKViewModel
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            quizItems.OptionList.forEach {
                OutlinedCard(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(
                        width = 2.dp,
                        color = if (it == selectedValue) Color.Green else Color.Gray
                    )

                ) {
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = it == selectedValue,
                                onClick = {
                                    selectedValue = it
                                    viewModel.onRadioButtonClicked(it)
                                }
                            ), verticalAlignment = Alignment.CenterVertically

                    ) {
                        RadioButton(
                            selected = it == selectedValue,
                            onClick = {
                                selectedValue = it
                                viewModel.onRadioButtonClicked(it)
                            }
                        )
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                    }

                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = onSubmitButtonClicked,
                enabled = selectedValue.isNotEmpty(),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Submit",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}

@Composable
fun ImageAndScoreCount(
    modifier: Modifier = Modifier,
    image : Int,  //here
    currentScore : Int,
    currentCount : Int
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Box(modifier = Modifier
            .size(dimensionResource(id = R.dimen.image_size_medium))
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.Count, currentCount),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.onPrimaryContainer)
                    .clip(shape = ShapeDefaults.Medium)
                    .weight(1f)
            )
            Text(
                text = stringResource(id = R.string.Score, currentScore),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.onPrimaryContainer)
                    .clip(shape = ShapeDefaults.Medium)
                    .weight(1f)
            )
        }
    }

}


@Composable
fun QuizScreenTopBar(
    modifier: Modifier = Modifier,
    currentSelectedQuiz : QuizItems,
    onBackButtonClicked: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackButtonClicked,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = CircleShape
                )
                .padding(start = 20.dp, top = 20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
        Text(
            text = currentSelectedQuiz.Question.toString(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}