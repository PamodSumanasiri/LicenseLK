package com.basics.licenselk

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.basics.licenselk.data.Theory
import com.basics.licenselk.data.local.TheoryList

@Composable
fun TheoryDetailScreen(
    modifier: Modifier = Modifier,
    onBackButtonClicked : () -> Unit,
    licenseLKUiState: LicenseLKUiState
) {

    val theoryList : List<Theory> = when(licenseLKUiState.currentSelectedTheoryCategory.title){
        R.string.cat1 -> TheoryList.warningList
        R.string.cat2 -> TheoryList.dangerList
        else -> {
            TheoryList.otherList
        }
    }
    BackHandler {
        onBackButtonClicked()
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TheoryDetailsScreenTopBar(
            onBackButtonClicked = onBackButtonClicked,
            licenseLKUiState = licenseLKUiState
        )
        Box(modifier = Modifier.padding(start = 32.dp)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                items(theoryList) {
                    TheoryGridItem(theory = it)
                }
            }
        }

    }


}

@Composable
fun TheoryGridItem(
    modifier: Modifier = Modifier,
    theory: Theory
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.image_size_small)),
            ) {
                Image(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .fillMaxSize()
                        .padding(
                            //top = dimensionResource(id = R.dimen.image_padding_top),
                            bottom = dimensionResource(id = R.dimen.image_padding_bottom)
                        ),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = theory.image) ,
                    contentDescription = stringResource(id = theory.title)
                )
            }
            Text(
                text = stringResource(id = theory.title),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


@Composable
fun TheoryDetailsScreenTopBar(
    onBackButtonClicked : () -> Unit,
    licenseLKUiState: LicenseLKUiState,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
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
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp)
        ) {
            Text(
                text =  stringResource(id = licenseLKUiState.currentSelectedTheoryCategory.title),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


