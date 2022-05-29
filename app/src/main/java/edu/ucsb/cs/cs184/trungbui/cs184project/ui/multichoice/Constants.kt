package edu.ucsb.cs.cs184.trungbui.cs184project.ui.multichoice

import edu.ucsb.cs.cs184.trungbui.cs184project.R
import edu.ucsb.cs.cs184.trungbui.cs184project.ui.multichoice.Question

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
            1, "What is the capital of Colorado?",
            "Denver", "Dover",
            "Lansing", "Des Moines", 1
        )

        questionsList.add(que1)

        // 2
        val que2 = Question(
            2, "What is the capital of Idaho?",
            "Olympia", "Lansing",
            "Springfield", "Boise", 4
        )

        questionsList.add(que2)

        // 3
        val que3 = Question(
            3, "What is the capital of California?",
            "San Francisco", "Sacramento",
            "Los Angeles", "Santa Barbara", 2
        )

        questionsList.add(que3)

        // 4
        val que4 = Question(
            4, "What is the capital of Alaska?",
            "Anchorage", "Juneau",
            "Badger", "Fairbanks", 2
        )

        questionsList.add(que4)

        // 5
        val que5 = Question(
            5, "What is the capital of Florida?",
            "Jacksonville", "Tampa",
            "Miami", "Tallahassee", 4
        )

        questionsList.add(que5)

        // 6
        val que6 = Question(
            6, "What is the capital of South Carolina?",
            "Columbia", "Charleston",
            "Clemson", "Myrtle Beach", 1
        )

        questionsList.add(que6)

        // 7
        val que7 = Question(
            7, "What is the capital of Ohio?",
            "Cleveland", "Toledo",
            "Columbus", "Cincinnati", 3
        )

        questionsList.add(que7)

        // 8
        val que8 = Question(
            8, "What is the capital of New York?",
            "New York", "Rochester",
            "Albany", "Albany", 4
        )

        questionsList.add(que8)

        // 9
        val que9 = Question(
            9, "What is the capital of Pennsylvania?",
            "Philadelphia","Harrisburg",
            "Pittsburgh", "Scranton", 2
        )

        questionsList.add(que9)

        // 10
        val que10 = Question(
            10, "What is the capital of Texas?",
            "Austin", "Dallas",
            "Houston", "San Antonio", 1
        )

        questionsList.add(que10)

        return questionsList
    }
}