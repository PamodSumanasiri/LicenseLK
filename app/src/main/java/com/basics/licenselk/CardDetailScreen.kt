package com.basics.licenselk

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.basics.licenselk.data.Tip


@Composable
fun CardDetailsPage(
    licenseLKUiState: LicenseLKUiState,
    onBackButtonClicked : () -> Unit,
    modifier: Modifier = Modifier
){
    BackHandler {
        onBackButtonClicked()
    }
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                .padding(top = dimensionResource(id = R.dimen.lazy_column_top))
        ){
            item {
                TipDetailsScreenTopBar(
                    onBackButtonClicked = onBackButtonClicked,
                    licenseLKUiState = licenseLKUiState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(id = R.dimen.top_bar_padding_bottom))
                )
                TipDetailsCard(
                    tip = licenseLKUiState.currentSelectedCard,
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.details_card_horizontal_padding))
                )
            }
        }
    }
}

@Composable
fun TipDetailsScreenTopBar(
    onBackButtonClicked : () -> Unit,
    licenseLKUiState: LicenseLKUiState,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
        ) {
        IconButton(
            onClick = onBackButtonClicked,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.icon_padding))
                .background(color = MaterialTheme.colorScheme.surface, shape = CircleShape)
            ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.icon_back)
            )
        }
        Text(
                text =  stringResource(id = licenseLKUiState.currentSelectedCard.subject),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
    }
}

@Composable
fun TipDetailsCard(
    tip : Tip,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.card_inner_padding))) {
            Text(
                text = stringResource(id = tip.subject),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelSmall
                )
            Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.size(dimensionResource(id = R.dimen.image_size))) {
                    Image(
                        painter = painterResource(id = tip.image),
                        contentDescription = stringResource(id = tip.tipNo),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Text(
                text = stringResource(id = tip.body),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}