package com.basics.licenselk

import android.content.Context
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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.basics.licenselk.data.Quiz
import com.basics.licenselk.data.QuizItems
import com.basics.licenselk.data.local.QuizLists


@Composable
fun QuizQuestionScreen(
    modifier: Modifier = Modifier,
    onSelectionChanged: (String) -> Unit,
    onSubmitButtonClicked: () -> Unit,
    onBackButtonClicked : () -> Unit,
    currentScore : Int,
    currentCount : Int,
    licenseLKUiState: LicenseLKUiState,
    quizItems: QuizItems  //Point is in here. when user select Paper 1 the UI should use the list1.
    // You can use when statements but the issue is we need a QuizItems not a List. can update the
    // uistate to currentSelectedQuizItem and then use
) {
//    val quizItems = when(licenseLKUiState.currentSelectedQuizCategory.paper){
//        R.string.quiz_cat1 -> QuizLists.paper1
//        R.string.quiz_cat2 -> QuizLists.paper2
//        else -> QuizLists.paper3
//
//    }
    Column(
        modifier = Modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen.column_padding_horizontal))
    ) {
        QuizScreenTopBar(
            Question = quizItems.Question,
            onBackButtonClicked = onBackButtonClicked
        )
        ImageAndScoreCount(
            Image = quizItems.Image,
            currentScore = currentScore,
            currentCount = currentCount
        )
        OptionListAndSubmitButton(
            quizItems = quizItems,
            onSelectionChanged = onSelectionChanged,
            onSubmitButtonClicked = onSubmitButtonClicked
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionListAndSubmitButton(
    modifier: Modifier = Modifier,
    quizItems: QuizItems,
    onSelectionChanged : (String) -> Unit,
    onSubmitButtonClicked : () -> Unit
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
                                    onSelectionChanged(it)
                                }
                            ), verticalAlignment = Alignment.CenterVertically

                    ) {
                        RadioButton(
                            selected = it == selectedValue,
                            onClick = {
                                selectedValue = it
                                onSelectionChanged(it)
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
    Image: Int,
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
                painter = painterResource(id = Image),
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
    Question: Int,
    onBackButtonClicked : () -> Unit,
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
            text = stringResource(id = Question),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}