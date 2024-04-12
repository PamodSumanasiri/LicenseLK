package com.basics.licenselk.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Tip(
    @DrawableRes val image : Int = -1,
    @StringRes val tipNo : Int = -1,
    @StringRes val subject : Int = -1,
    @StringRes val body : Int = -1
)
