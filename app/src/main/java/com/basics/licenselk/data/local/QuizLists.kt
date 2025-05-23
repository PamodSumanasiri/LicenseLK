package com.basics.licenselk.data.local

import com.basics.licenselk.R
import com.basics.licenselk.data.Quiz
import com.basics.licenselk.data.QuizItems

object QuizLists {

    val quizCategory : List<Quiz> = listOf(
        Quiz(R.drawable.paper,R.string.quiz_cat1),
        Quiz(R.drawable.paper,R.string.quiz_cat2),
        Quiz(R.drawable.paper,R.string.quiz_cat3)
    )

    val paper1 : List<QuizItems> = listOf(
        QuizItems("What is this",R.drawable.t5, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t3, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t4, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t2, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t6, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t7, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t8, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t9, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t2, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t3, listOf("Option1","Option2","Option 3","Option 4"),"Option1")
        )

    val paper2 : List<QuizItems> = listOf(
        QuizItems("What is this",R.drawable.d1, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t3, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.d1, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t2, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.d1, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t7, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.d1, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t9, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.d1, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t3, listOf("Option1","Option2","Option 3","Option 4"),"Option1")
    )

    val paper3 : List<QuizItems> = listOf(
        QuizItems("What is this",R.drawable.o1, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t3, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.o1, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t2, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.o1, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t7, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.o1, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t9, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.o1, listOf("Option1","Option2","Option 3","Option 4"),"Option1"),
        QuizItems("What is this",R.drawable.t3, listOf("Option1","Option2","Option 3","Option 4"),"Option1")
    )

    val defaultQuizItems = QuizItems("What is this",-1, listOf("","","",""),"")


    val defaultQuizCategory = Quiz(-1,-1)
}