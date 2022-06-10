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

        val que11 = Question(
            11, 31.48, -87.71, "Wisconsin",
            "Kentucky", "Alabama", "Maryland", 3
        )

        questionsList.add(que11)

        val que12 = Question(
            12, 46.23, -87.19, "Nebraska",
            "Nevada", "Michigan", "Oklahoma", 3
        )

        questionsList.add(que12)

        val que13 = Question(
            13, 37.80, -83.71, "Hawaii",
            "Kentucky", "South Carolina", "Nebraska", 2
        )

        questionsList.add(que13)

        val que14 = Question(
            14, 37.80, -97.19, "Missouri",
            "Delaware", "Kansas", "New Mexico", 3
        )

        questionsList.add(que14)

        val que15 = Question(
            15, 39.58, -79.19, "Colorado",
            "Nevada", "Wisconsin", "Maryland", 4
        )

        questionsList.add(que15)

        val que16 = Question(
            16, 38.95, -79.83, "Kentucky",
            "Washington", "Iowa", "West Virginia", 4
        )

        questionsList.add(que16)

        val que17 = Question(
            17, 30.93, -98.89, "South Dakota",
            "Connecticut", "Texas", "Washington", 3
        )

        questionsList.add(que17)

        val que18 = Question(
            18, 44.89, -99.24, "South Dakota",
            "New Jersey", "Florida", "Wyoming", 1
        )

        questionsList.add(que18)

        val que19 = Question(
            19, 65.53, -155.44, "Missouri",
            "Alaska", "New York", "West Virginia", 2
        )

        questionsList.add(que19)

        val que20 = Question(
            20, 63.89, -144.34, "Utah",
            "Alabama", "Alaska", "New Jersey", 3
        )

        questionsList.add(que20)

        val que21 = Question(
            21, 41.99, -89.38, "South Carolina",
            "Idaho", "New Mexico", "Illinois", 4
        )

        questionsList.add(que21)

        val que22 = Question(
            22, 38.58, -75.38, "Delaware",
            "New Hampshire", "Mississippi", "Ohio", 1
        )

        questionsList.add(que22)

        val que23 = Question(
            23, 41.78, -72.89, "Pennsylvania",
            "Mississippi", "Connecticut", "Colorado", 3
        )

        questionsList.add(que23)

        val que24 = Question(
            24, 39.50, -75.65, "Maine",
            "South Carolina", "Delaware", "Oregon", 3
        )

        questionsList.add(que24)

        val que25 = Question(
            25, 35.18, -91.66, "Arkansas",
            "Connecticut", "Georgia", "Delaware", 1
        )

        questionsList.add(que25)

        val que26 = Question(
            26, 41.74, -72.07, "Arizona",
            "Rhode Island", "Connecticut", "Arkansas", 3
        )

        questionsList.add(que26)

        val que27 = Question(
            27, 42.88, -95.46, "Iowa",
            "Virginia", "Hawaii", "Montana", 1
        )

        questionsList.add(que27)

        val que28 = Question(
            28, 31.36, -92.37, "Hawaii",
            "Louisiana", "California", "Wisconsin", 2
        )

        questionsList.add(que28)

        val que29 = Question(
            29, 42.54, -72.72, "Pennsylvania",
            "Maryland", "Illinois", "Massachusetts", 4
        )

        questionsList.add(que29)

        val que30 = Question(
            30, 19.75, -155.47, "Connecticut",
            "Hawaii", "Wisconsin", "Mississippi", 2
        )

        questionsList.add(que30)

        val que31 = Question(
            31, 38.95, -115.86, "Alaska",
            "Rhode Island", "Nevada", "Georgia", 3
        )

        questionsList.add(que31)

        val que32 = Question(
            32, 33.93, -85.75, "Alabama",
            "New Hampshire", "Rhode Island", "New York", 1
        )

        questionsList.add(que32)

        val que33 = Question(
            33, 35.66, -77.42, "Kansas",
            "Texas", "West Virginia", "North Carolina", 4
        )

        questionsList.add(que33)

        val que34 = Question(
            34, 32.65, -112.77, "Rhode Island",
            "Wyoming", "Arizona", "Colorado", 3
        )

        questionsList.add(que34)

        val que35 = Question(
            35, 47.50, -95.33, "Pennsylvania",
            "South Dakota", "Minnesota", "Tennessee", 3
        )

        questionsList.add(que35)

        val que36 = Question(
            36, 42.16, -105.39, "Florida",
            "North Carolina", "Mississippi", "Wyoming", 4
        )

        questionsList.add(que36)

        val que37 = Question(
            37, 35.02, -100.92, "Arizona",
            "Texas", "Georgia", "Wyoming", 2
        )

        questionsList.add(que37)

        val que38 = Question(
            38, 34.59, -115.42, "Nebraska",
            "Illinois", "North Carolina", "California", 4
        )

        questionsList.add(que38)

        val que39 = Question(
            39, 46.49, -102.84, "North Dakota",
            "Idaho", "Kansas", "Tennessee", 1
        )

        questionsList.add(que39)

        val que40 = Question(
            40, 34.35, -113.85, "Oregon",
            "California", "Hawaii", "Arizona", 4
        )

        questionsList.add(que40)

        val que41 = Question(
            41, 40.18, -92.47, "Idaho",
            "Oklahoma", "Alaska", "Missouri", 4
        )

        questionsList.add(que41)

        val que42 = Question(
            42, 41.84, -71.56, "Rhode Island",
            "Delaware", "West Virginia", "Wisconsin", 1
        )

        questionsList.add(que42)

        val que43 = Question(
            43, 37.33, -77.59, "Virginia",
            "Hawaii", "North Carolina", "North Dakota", 1
        )

        questionsList.add(que43)

        val que44 = Question(
            44, 44.63, -121.82, "South Carolina",
            "Alabama", "Michigan", "Oregon", 4
        )

        questionsList.add(que44)

        val que45 = Question(
            45, 41.65, -79.91, "Pennsylvania",
            "Nevada", "Montana", "South Carolina", 1
        )

        questionsList.add(que45)

        val que46 = Question(
            46, 38.58, -95.62, "Kansas",
            "Massachusetts", "Virginia", "Michigan", 1
        )

        questionsList.add(que46)

        val que47 = Question(
            47, 43.34, -84.57, "West Virginia",
            "Michigan", "Oregon", "Ohio", 2
        )

        questionsList.add(que47)

        val que48 = Question(
            48, 41.84, -73.11, "Iowa",
            "New Mexico", "Kentucky", "Connecticut", 4
        )

        questionsList.add(que48)

        val que49 = Question(
            49, 42.05, -99.06, "Texas",
            "Nebraska", "Idaho", "Rhode Island", 2
        )

        questionsList.add(que49)

        val que50 = Question(
            50, 38.63, -75.43, "Mississippi",
            "Georgia", "South Dakota", "Delaware", 4
        )

        questionsList.add(que50)

        return questionsList
    }
}