package com.basics.licenselk

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.basics.licenselk.data.TheoryCard
import com.basics.licenselk.data.Tip


@Composable
fun TheoryScreen(
    licenseLKUiState: LicenseLKUiState,
    onclick : (TheoryCard) -> Unit,
    onBackButtonClicked : () -> Unit,
    modifier: Modifier = Modifier
){
    if (licenseLKUiState.isShowingTheoryPage){
        TheoriesScreen(
            onclick = onclick,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.bottom_bar_padding_bottom))
        )
    }else{
        TheoryDetailScreen(
            onBackButtonClicked = onBackButtonClicked,
            licenseLKUiState = licenseLKUiState,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.bottom_bar_padding_bottom))
        )
    }
}