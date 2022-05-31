package edu.ucsb.cs.cs184.trungbui.cs184project.ui.google_maps

data class Question(
    val id: Int,
    val lat: Float,
    val long: Float,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int
)