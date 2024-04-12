package com.basics.licenselk

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.basics.licenselk.data.Tip
import com.basics.licenselk.data.local.TipList
import com.basics.licenselk.data.local.TipList.tipList

@Composable
fun CardList(
    modifier: Modifier = Modifier,
    onCardClick: (Tip) -> Unit //Mind Here to change if necessary
    //Or you can add more onClick parameters to apply those to different screen onclick functions.
)
{
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.card_list_vertical_padding))
    ){
        item {
            AppTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.top_bar_padding_vertical))
            )
        }
        items(tipList){
            TipCard(
                tip = it,
                selected = false,
                onCardClick = { onCardClick(it) },
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.lazy_column_horizontal_padding), vertical = 2.dp ))
        }


    }
    //SpacerForAll(modifier = Modifier.fillMaxWidth())
}

//@Preview(showSystemUi = true)
//@Composable
//fun CardListPr(){
//    CardList( )
//}
@Composable
fun SpacerForAll(modifier: Modifier = Modifier) {
    Row {
        Spacer(modifier = Modifier.weight(1f))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipCard(
    tip: Tip,
    selected : Boolean,
    onCardClick:()->Unit,
    modifier: Modifier = Modifier
){
  Card(
      modifier = modifier,
      colors = CardDefaults.cardColors(
          containerColor = if (selected)
              MaterialTheme.colorScheme.primaryContainer
          else
              MaterialTheme.colorScheme.secondaryContainer
      ),
      onClick = onCardClick
      ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.card_inner_padding)))
        {
            CardHeader(tip = tip, modifier = Modifier.fillMaxWidth())
            Text(
                text = stringResource(id = tip.subject),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.subject_padding_top),
                        bottom = dimensionResource(id = R.dimen.subject_padding_bottom))
                )
            Text(
                text = stringResource(id = tip.body),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
  }
}

@Composable
fun CardHeader(tip : Tip,modifier: Modifier = Modifier){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.card_header_horizontal_padding)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TopBarImage(
            drawableRes = tip.image,
            description = tip.tipNo,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.card_header_image_size)))
        Text(text = stringResource(id = tip.tipNo), style = MaterialTheme.typography.labelMedium)
    }
}


@Composable
fun TopBarImage(
    @DrawableRes drawableRes : Int,
    description : Int,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier) {
        Image(
            modifier = Modifier.clip(CircleShape),
            painter = painterResource(id = drawableRes),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = description) ,

        )
    }
}


@Composable
fun AppLogo(modifier: Modifier =  Modifier,color: Color = MaterialTheme.colorScheme.primary){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.app_logo),
        colorFilter = ColorFilter.tint(color),
        modifier = modifier
    )
}


@Composable
fun AppTopBar(modifier: Modifier = Modifier){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
        ) {
        AppLogo(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.logo_size))
                    .padding(start = dimensionResource(id = R.dimen.logo_padding_start))
            )
        TopBarImage(
            drawableRes = R.drawable.top_bar_image,
            description = R.string.top_image,
            modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.top_image_padding_end))
                .size(dimensionResource(id = R.dimen.top_image_size))
        )

    }
}
//
//@Preview(showSystemUi = true)
//@Composable
//fun AppTopBarPr(){
//    AppTopBar()
//}