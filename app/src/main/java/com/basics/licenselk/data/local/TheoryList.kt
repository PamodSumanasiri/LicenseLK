package com.basics.licenselk.data.local

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.basics.licenselk.LicenseLKUiState
import com.basics.licenselk.LicenseLKViewModel
import com.basics.licenselk.R
import com.basics.licenselk.data.Theory
import com.basics.licenselk.data.TheoryCard

object TheoryList {


    val theoryCategoryList : List<TheoryCard> = listOf(
        TheoryCard(R.drawable.t1,R.string.cat1),
        TheoryCard(R.drawable.d1,R.string.cat2),
        TheoryCard(R.drawable.o1,R.string.cat3),
    )

    val warningList : List<Theory> = listOf(
        Theory(R.drawable.t1,R.string.t1),
        Theory(R.drawable.t2,R.string.t2),
        Theory(R.drawable.t3,R.string.t3),
        Theory(R.drawable.t4,R.string.t4),
        Theory(R.drawable.t5,R.string.t5),
        Theory(R.drawable.t6,R.string.t6),
        Theory(R.drawable.t7,R.string.t7),
        Theory(R.drawable.t8,R.string.t8),
        Theory(R.drawable.t9,R.string.t9),
        Theory(R.drawable.t10,R.string.t10)
    )
    val dangerList : List<Theory> = listOf(
        Theory(R.drawable.d1,R.string.d1),
        Theory(R.drawable.d1,R.string.d1),
        Theory(R.drawable.d1,R.string.d1),
        Theory(R.drawable.d1,R.string.d1),
        Theory(R.drawable.d1,R.string.d1),
        Theory(R.drawable.d1,R.string.d1),
        Theory(R.drawable.d1,R.string.d1),
        Theory(R.drawable.d1,R.string.d1),
        Theory(R.drawable.d1,R.string.d1),
        Theory(R.drawable.d1,R.string.d1),
    )
    val  otherList : List<Theory> = listOf(
        Theory(R.drawable.o1,R.string.o1),
        Theory(R.drawable.o1,R.string.o1),
        Theory(R.drawable.o1,R.string.o1),
        Theory(R.drawable.o1,R.string.o1),
        Theory(R.drawable.o1,R.string.o1),
        Theory(R.drawable.o1,R.string.o1),
        Theory(R.drawable.o1,R.string.o1),
        Theory(R.drawable.o1,R.string.o1),
        Theory(R.drawable.o1,R.string.o1),
        Theory(R.drawable.o1,R.string.o1),
    )

    val defaultTheory : TheoryCard = TheoryCard(-1,-1)

}