package com.basics.licenselk

import android.content.Context
import androidx.lifecycle.ViewModel
import com.basics.licenselk.data.Quiz
import com.basics.licenselk.data.ScreenType
import com.basics.licenselk.data.TheoryCard
import com.basics.licenselk.data.Tip
import com.basics.licenselk.data.local.TheoryList.defaultTheory
import com.basics.licenselk.data.local.TipList.default_tip
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LicenseLKViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LicenseLKUiState())
    val uiState : StateFlow<LicenseLKUiState> = _uiState

    fun updateDetailScreenState (tip : Tip){
        _uiState.update {
            it.copy(
                currentSelectedCard = tip,
                isShowingHomePage = false
            )
        }
    }

    fun  updateTheoryScreenState (theoryCard: TheoryCard){
        _uiState.update {
            it.copy(
                currentSelectedTheoryCategory = theoryCard,
                isShowingTheoryPage = false
            )
        }
    }

    fun  updateQuizScreenState (quizCategory : Quiz){
        _uiState.update {
            it.copy(
                currentSelectedQuizCategory = quizCategory,
                isShowingQuizPage = false
            )
        }

    }

    fun resetHomeScreenState(){
        _uiState.update {
            it.copy(
                isShowingHomePage = true,
                currentSelectedCard = default_tip
            )
        }
    }

    fun resetTheoryScreenState(){
        _uiState.update {
            it.copy(
                isShowingTheoryPage = true,
                currentSelectedTheoryCategory = defaultTheory
            )
        }
    }

    fun updateCurrentScreen(screenType: ScreenType){
        _uiState.update {
            it.copy(
                screenType = screenType
            )
        }
    }


}