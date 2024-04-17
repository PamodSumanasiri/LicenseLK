package com.basics.licenselk

import com.basics.licenselk.data.Quiz
import com.basics.licenselk.data.QuizItems
import com.basics.licenselk.data.ScreenType
import com.basics.licenselk.data.Theory
import com.basics.licenselk.data.TheoryCard
import com.basics.licenselk.data.Tip
import com.basics.licenselk.data.local.QuizLists
import com.basics.licenselk.data.local.TheoryList
import com.basics.licenselk.data.local.TipList
import com.basics.licenselk.data.local.TipList.default_tip

data class LicenseLKUiState(
    val currentSelectedCard: Tip = default_tip,
    val currentSelectedTheoryCategory: TheoryCard = TheoryList.defaultTheory,
    val currentSelectedQuizCategory: Quiz = QuizLists.defaultQuizCategory,

    val currentSelectedQuizItems: QuizItems = QuizLists.defaultQuizItems,
    val currentSelectedOption: String = "",
    val isFinalRound: Boolean = false,
    val isGameOver: Boolean = false,
    val leaveAlert: Boolean = false,

    val isShowingHomePage: Boolean = true,
    val isShowingTheoryPage: Boolean = true,
    val isShowingQuizPage: Boolean = true,

    val screenType: ScreenType = ScreenType.Tip,

    //Necessary?
    //val currentScore: Int = 0,



    val question: String = "What is this",
    val image: Int = R.drawable.t5,
    val optionList: List<String> = listOf(),
    val correctAnswer:String = "2",
    val score: Int = 0,
    val currentCount: Int =1,
    val isQuizOver: Boolean = false


)
