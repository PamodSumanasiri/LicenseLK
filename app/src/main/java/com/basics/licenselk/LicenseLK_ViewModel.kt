package com.basics.licenselk

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basics.licenselk.data.Quiz
import com.basics.licenselk.data.QuizItems
import com.basics.licenselk.data.ScreenType
import com.basics.licenselk.data.TheoryCard
import com.basics.licenselk.data.Tip
import com.basics.licenselk.data.local.QuizLists
import com.basics.licenselk.data.local.TheoryList.defaultTheory
import com.basics.licenselk.data.local.TipList.default_tip
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.w3c.dom.ls.LSInput

class LicenseLKViewModel : ViewModel() {

//    init {
//        initializeRandomSet()
//    }

    private val _uiState = MutableStateFlow(LicenseLKUiState())
    val uiState : StateFlow<LicenseLKUiState> = _uiState
    var quizList : List<QuizItems>? = null

    fun getPaperCategory(categroy:Quiz){
        quizList  = when(categroy.paper){
            R.string.quiz_cat1 -> QuizLists.paper1
            R.string.quiz_cat2 -> QuizLists.paper2
            else -> QuizLists.paper3
        }
    }

//    private val usedSets : MutableSet<QuizItems> = mutableSetOf()
//    private lateinit var randomSet : QuizItems
    var correctAnswer : Boolean? = null

    var usedSets : MutableSet<QuizItems> = mutableSetOf()
    lateinit var randomSet : QuizItems
    private var currentSet : QuizItems? = null
    private var userSelectedOption : String? = null
    var isAnswerCorrect : Boolean? = null
    private var isRandomSetInitialized: Boolean = false
    //var showAlertDialog :Boolean = false
    var isIncorrectTenthAnswer: Boolean = false
    var showAlertDialog by mutableStateOf(false)

    fun initializeRandomSet() {
        if (!isRandomSetInitialized) {
            quizList?.let { getRandomSet(it) }
            isRandomSetInitialized = true
        }
    }

    fun getRandomSet(signType : List<QuizItems>){
        Log.d("RandomSet", "RandomSet invoked")
        if (usedSets.size < 10){
            do {
                randomSet = signType.random()
            } while (usedSets.contains(randomSet))

            usedSets.add(randomSet)
            currentSet = randomSet

            updateUiStateWithCurrentSet(_uiState.value.score)
        }
    }

     fun onNextButtonClicked(){
        val isCorrect = userSelectedOption == currentSet?.CorrectOption?.trim()
         Log.d("AnswerCheck", "User selected option: $userSelectedOption")
         Log.d("AnswerCheck", "Correct option: ${currentSet?.CorrectOption}")
         Log.d("AnswerCheck", "Is correct: $isCorrect")
        isAnswerCorrect = isCorrect
        if (isCorrect) {
            Log.d("AnswerCheck", "Selected option is correct")
            if (usedSets.size<10){
                quizList?.let { getRandomSet(it) }

                val updateScore = _uiState.value.score.plus(10)
                updateUiStateWithCurrentSet(updateScore)

                _uiState.update {
                    it.copy(currentCount = it.currentCount.inc())
                }
            }else {
                quizList?.let { getRandomSet(it) }
                val updateScore = _uiState.value.score.plus(10)
                updateUiStateWithCurrentSet(updateScore)
                _uiState.update { it.copy(isQuizOver = true) }
            }
        }else{
            Log.d("AnswerCheck", "Selected option is incorrect")
            updateUiStateWithCurrentSet(_uiState.value.score)
            quizList?.let { delayAndMoveToNextQuestion(it) }
            if (!uiState.value.isQuizOver){

                _uiState.update {
                    it.copy(currentCount = it.currentCount.inc())
                }
            }
        }
    }

    private fun updateUiStateWithCurrentSet(updateScore: Int) {
        if (usedSets.size < 11) { // Check if currentCount is less than 10
            _uiState.update {
                it.copy(
                    question = currentSet?.Question?.toString() ?: "Default question",
                    image = currentSet?.Image ?: 0,
                    optionList = currentSet?.OptionList.orEmpty(),
                    score = updateScore,
                    correctAnswer = currentSet?.CorrectOption.orEmpty(),
                    // currentCount = it.currentCount.inc()
                )
            }
        } else {
            _uiState.update {
                it.copy(isQuizOver = true, score = updateScore)
            }
        }
    }

    private fun delayAndMoveToNextQuestion(signType : List<QuizItems>){
        viewModelScope.launch {
            delay(2000)
            //getRandomSet(signType)
            isAnswerCorrect = null
            if (usedSets.size == 10 ) {
                isIncorrectTenthAnswer = true
                //resetQuiz(signType)
            }else getRandomSet(signType)
        }
    }

    fun resetQuizState(signType : List<QuizItems>){
        usedSets.clear()
        _uiState.update {
            it.copy(
                currentCount = 1,
                score = 0,
                isQuizOver = false
            )
        }
        showAlertDialog = false
        getRandomSet(signType)
    }






















//    fun getRandomSet(){
//        correctAnswer = null
//        if (usedSets.size<10){
//            do {
//                randomSet = quizList?.random()!!
//            } while (usedSets.contains(randomSet))
//
//            usedSets.add(randomSet)
//            _uiState.update {
//                it.copy(
//                    currentSelectedQuizItems = randomSet
//                )
//            }
//        }
//        else _uiState.update { it.copy(isFinalRound = true) }
//    }

    fun onRadioButtonClicked(option : String){
        userSelectedOption = option
        _uiState.update {
            it.copy(
                currentSelectedOption = option,
            )
        }
    }

//    fun onSubmitButtonClicked(){
//        val isAnswerCorrect = uiState.value.currentSelectedQuizItems.CorrectOption == uiState.value.currentSelectedOption
//        correctAnswer = isAnswerCorrect
//        if(!uiState.value.isFinalRound){
//            if (isAnswerCorrect) {
//                getRandomSet()
//                val updatedScore = _uiState.value.currentScore.plus(10)
//                _uiState.update {
//                    it.copy(
//                        currentScore = updatedScore,
//                        currentCount = it.currentCount.inc()
//                    )
//                }
//            } else { //delay here
//                delayAndMoveToNextQuestion()
//                getRandomSet()
//                _uiState.update {
//                    it.copy(
//                        currentCount = it.currentCount.inc()
//                    )
//                }
//            }
//        }else {
//            if (isAnswerCorrect) {
//                val updatedScore = _uiState.value.currentScore.plus(10)
//                _uiState.update {
//                    it.copy(
//                        currentScore = updatedScore,
//                        isGameOver = true
//                    )
//                }
//            }else { //delay here
//                delayAndMoveToNextQuestion()
//                _uiState.update {
//                    it.copy(
//                        isGameOver = true
//                    )
//                }
//            }
//        }
//    }

    private fun delayAndMoveToNextQuestion(){
        viewModelScope.launch {
            delay(2000)

        }
    }

    fun onQuizBackButtonClicked(){
        _uiState.update {
            it.copy(
                leaveAlert = true
            )
        }
    }
    fun onQuizDismissButtonClicked(){
        _uiState.update {
            it.copy(
                leaveAlert = false
            )
        }
    }

    fun onPlayAgainClicked(){
        _uiState.update {
            it.copy(
                isGameOver = false,
                currentCount = 1,
                score = 0
            )
        }
        usedSets.clear()
        quizList?.let { getRandomSet(it) }
    }




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

    fun  updateQuizScreenState (){
        _uiState.update {
            it.copy(
//                currentSelectedQuizCategory = quizCategory,
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