package com.basics.licenselk


import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.basics.licenselk.ui.theme.Shapes
import com.basics.licenselk.ui.theme.noto_sherif_bold
import com.basics.licenselk.ui.theme.noto_sherif_regular

@Composable
fun QuizQuestionScreenNew(
    modifier: Modifier = Modifier,
    viewModel : LicenseLKViewModel,
    onSelectionChanged : (String) -> Unit,
    onNextButtonClicked :() -> Unit,
    navigateBack: ()-> Unit,
    onBackButtonClicked : ()-> Unit,
    playAgain: ()-> Unit
){
    var selectedValue by rememberSaveable { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    //var showAlertDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.padding_large))
        ) {
            Column (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 16.dp), horizontalAlignment = Alignment.Start){
                Row (
                    modifier = modifier,
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(
                        onClick = onBackButtonClicked,
                        modifier = Modifier
                            .background(
                                color = colorScheme.onSurface,
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
                        text = uiState.question,
                        textAlign = TextAlign.Start,
                        style = typography.bodyLarge,
                        color = colorScheme.onPrimary,
                        fontFamily = noto_sherif_bold,

                        )
                }
            }
            Box (modifier = Modifier.size(300.dp),){
                Image(
                    painter = painterResource(id = uiState.image),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.padding_large))) {
                Text(
                    text = stringResource(id = R.string.Score,uiState.score),
                    modifier = Modifier
                        .clip(Shapes.medium)
                        .weight(1f)
                        .background(color = colorScheme.onSurface),
                    textAlign = TextAlign.Center,
                    color = colorScheme.onPrimary
                )
                Text(
                    text = stringResource(id = R.string.Count,uiState.currentCount),
                    modifier = Modifier
                        .clip(Shapes.medium)
                        .weight(1f)
                        .background(color = colorScheme.onSurface),
                    textAlign = TextAlign.Center,
                    color = colorScheme.onPrimary
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        Column(modifier = Modifier.fillMaxWidth() ) {
            uiState.optionList.forEach{
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = it==selectedValue,
                            onClick = {
                                selectedValue = it
                                onSelectionChanged(it)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    RadioButton(selected = it==selectedValue,
                        onClick = {
                            selectedValue = it
                            onSelectionChanged(it)
                        }
                    )
                    Text(
                        text = it,
                        style = typography.bodyMedium,
                        color = colorScheme.onPrimary,
                        fontFamily = noto_sherif_regular
                    )
                }
            }

        }
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
            //verticalAlignment = Alignment.Bottom,

        ) {

            Button(onClick =  onNextButtonClicked , modifier =  Modifier.weight(1f), enabled = selectedValue.isNotEmpty()) {
                Text(text = "Submit")
            }
        }
    }

    if (viewModel.isAnswerCorrect == false){
        LaunchedEffect(uiState){
            delay(2000)
            onNextButtonClicked()
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)) {
            Text(
                text = "Correct Answer is ${uiState.correctAnswer}",
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                color = colorScheme.onPrimary
            )
        }

    }

    if (uiState.leaveAlert){
        AlertDialog2(dismiss = { viewModel.onQuizDismissButtonClicked() }, navigateBack =  navigateBack )
    }

    LaunchedEffect(uiState) {
        if (viewModel.isIncorrectTenthAnswer && viewModel.usedSets.size == 10) {
            delay(2000)
            viewModel.showAlertDialog = true
        }
    }


    if (uiState.isQuizOver || viewModel.showAlertDialog){
        AlertDialog(
            score = uiState.score,
            playAgain = playAgain,
            navigateBack = navigateBack
        )
    }
}

@Composable
private fun AlertDialog(
    score: Int,
    playAgain: ()-> Unit,
    //navigateBack: ()-> Unit,
    modifier: Modifier = Modifier,
    navigateBack: ()-> Unit
){

    androidx.compose.material3.AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text(text = stringResource(id = R.string.congratulation))},
        text = { Text(text = stringResource(id = R.string.your_score,score))},
        modifier = modifier,
        dismissButton = { TextButton(onClick = { navigateBack() }) {
            Text(text = stringResource(id = R.string.back_to_menu))
        }},
        confirmButton = { TextButton(onClick = playAgain) {
            Text(text = stringResource(id = R.string.play_again))
        }}


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

