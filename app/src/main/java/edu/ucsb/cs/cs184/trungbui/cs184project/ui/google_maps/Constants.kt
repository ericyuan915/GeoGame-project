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
            3, 41.25, -76.22, "New York",
            "Pennsylvania", "Nevada", "Connecticut", 2
        )

        questionsList.add(que3)

        // 4
        val que4 = Question(
            4, 30.08, -97.29, "Texas",
            "New Mexico", "Idaho", "Kentucky", 1
        )

        questionsList.add(que4)

        // 5
        val que5 = Question(
            5, 41.56, -94.29, "South Dakota",
            "California", "Iowa", "Montana", 3
        )

        questionsList.add(que5)

        // 6
        val que6 = Question(
            6, 37.84, -89.09, "Illinois",
            "New York", "Maryland", "Delaware", 1
        )

        questionsList.add(que6)

        // 7
        val que7 = Question(
            7, 43.08, -91.69, "Michigan",
            "Illinois", "Oregon", "Iowa", 4
        )

        questionsList.add(que7)

        // 8
        val que8 = Question(
            8, 34.58, -95.86, "South Carolina",
            "New Mexico", "Arizona", "Oklahoma", 4
        )

        questionsList.add(que8)

        // 9
        val que9 = Question(
            9, 44.81, -122.18, "Arizona",
            "Oregon", "North Carolina", "Ohio", 2
        )

        questionsList.add(que9)

        // 10
        val que10 = Question(
            10, 37.75, -87.40, "Oklahoma",
            "Kentucky", "California", "South Carolina", 2
        )

        questionsList.add(que10)


        return questionsList
    }
}