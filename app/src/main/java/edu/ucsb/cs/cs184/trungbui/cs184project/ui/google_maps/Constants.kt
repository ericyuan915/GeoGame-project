package edu.ucsb.cs.cs184.trungbui.cs184project.ui.google_maps

import edu.ucsb.cs.cs184.trungbui.cs184project.R
import edu.ucsb.cs.cs184.trungbui.cs184project.ui.google_maps.Question

object Constants {

    // TODO (STEP 1: Create a constant variables which we required in the result screen.)
    // START
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    // END

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // 1
        val que1 = Question(
            1, 41.72, -107.01, "Wyoming",
            "Idaho", "Illinois", "Florida", 1
        )

        questionsList.add(que1)

        // 2
        val que2 = Question(
            2, 39.53, -123.10, "Oregon",
            "Virginia", "Florida", "California", 4
        )

        questionsList.add(que2)

        // 3
        val que3 = Question(
            3, 41.25, 30.08, "New York",
            "Pennsylvania", "Nevada", "Connecticut", 2
        )

        questionsList.add(que3)

        // 4
        val que4 = Question(
            4, 30.08, -97.29, "Texas",
            "New Mexico", "Idaho", "Kentucky", 1
        )

        questionsList.add(que4)

        return questionsList
    }
}