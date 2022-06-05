package edu.ucsb.cs.cs184.trungbui.cs184project.ui.multichoice

data class Question(
    val id: Int,
    val difficulty: Char, // e, m, d
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int
)