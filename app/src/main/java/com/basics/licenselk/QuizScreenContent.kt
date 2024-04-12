package com.basics.licenselk

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.basics.licenselk.data.Quiz
import com.basics.licenselk.data.TheoryCard
import com.basics.licenselk.data.local.QuizLists
import com.basics.licenselk.data.local.TheoryList

@Composable
fun QuizCategoryScreen(
    modifier: Modifier= Modifier,
    onclick: (Quiz) -> Unit,
    context: Context
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.card_list_vertical_padding))
    )
    {
        item {
            AppTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.top_bar_padding_vertical))
            )
        }
        items(QuizLists.quizCategory){
            QuizCategoryCard(
                QuizCategory = it,
                onclick = onclick,
                context = context
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizCategoryCard(
    QuizCategory: Quiz,
    modifier: Modifier = Modifier,
    onclick : (Quiz) -> Unit,
    context: Context
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(
            horizontal = dimensionResource(id = R.dimen.column_padding_horizontal),
            vertical = dimensionResource(
                id = R.dimen.card_list_vertical_padding
            )
        ),
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            onClick = {
                onclick(QuizCategory)
                val intent = Intent(context,QuizActivity::class.java).apply {
                    putExtra("quizCategory",QuizCategory)
                }
                context.startActivity(intent)  //new
            }
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                CardInnerDetails(QuizCategory = QuizCategory)
            }
        }
    }
}

@Composable
private fun CardInnerDetails(
    modifier: Modifier = Modifier,
    QuizCategory : Quiz
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.card_inner_padding)),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.image_size_small)),
        ) {
            Image(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = QuizCategory.image) ,
                contentDescription = stringResource(id = QuizCategory.paper)
            )
        }
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = QuizCategory.paper),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}