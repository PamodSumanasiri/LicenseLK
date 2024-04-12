package com.basics.licenselk.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Theory(
    @DrawableRes val image : Int,
    @StringRes val title : Int,
    //@StringRes val description : Int,
    //val value : Int
)

data class TheoryCard(
    @DrawableRes val image : Int,
    @StringRes val title : Int,
)
