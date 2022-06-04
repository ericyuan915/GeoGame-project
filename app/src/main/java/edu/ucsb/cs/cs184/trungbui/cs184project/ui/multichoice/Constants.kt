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
            1,'m', "What is the capital of Colorado?",
            "Denver", "Dover",
            "Lansing", "Des Moines", 1
        )

        questionsList.add(que1)

        // 2
        val que2 = Question(
            2, 'h',"What is the capital of Idaho?",
            "Olympia", "Lansing",
            "Springfield", "Boise", 4
        )

        questionsList.add(que2)

        // 3
        val que3 = Question(
            3, 'e',"What is the capital of California?",
            "San Francisco", "Sacramento",
            "Los Angeles", "Santa Barbara", 2
        )

        questionsList.add(que3)

        // 4
        val que4 = Question(
            4, 'h',"What is the capital of Alaska?",
            "Anchorage", "Juneau",
            "Badger", "Fairbanks", 2
        )

        questionsList.add(que4)

        // 5
        val que5 = Question(
            5, 'e',"What is the capital of Florida?",
            "Jacksonville", "Tampa",
            "Miami", "Tallahassee", 4
        )

        questionsList.add(que5)

        // 6
        val que6 = Question(
            6, 'h',"What is the capital of South Carolina?",
            "Columbia", "Charleston",
            "Clemson", "Myrtle Beach", 1
        )

        questionsList.add(que6)

        // 7
        val que7 = Question(
            7, 'h',"What is the capital of Ohio?",
            "Cleveland", "Toledo",
            "Columbus", "Cincinnati", 3
        )

        questionsList.add(que7)

        // 8
        val que8 = Question(
            8, 'e',"What is the capital of New York?",
            "New York", "Rochester",
            "Syracuse", "Albany", 4
        )

        questionsList.add(que8)

        // 9
        val que9 = Question(
            9, 'm',"What is the capital of Pennsylvania?",
            "Philadelphia","Harrisburg",
            "Pittsburgh", "Scranton", 2
        )

        questionsList.add(que9)

        // 10
        val que10 = Question(
            10, 'e',"What is the capital of Texas?",
            "Austin", "Dallas",
            "Houston", "San Antonio", 1
        )

        questionsList.add(que10)

        // 11
        val que11 = Question(
            11, 'e',"What is the most populous state?",
            "California", "Florida",
            "New York", "Texas", 1
        )

        questionsList.add(que11)

        // 12
        val que12 = Question(
            12, 'm',"Which state has the longest shoreline?",
            "California", "Alaska",
            "Florida", "Texas", 2
        )

        questionsList.add(que12)

        // 13
        val que13 = Question(
            13, 'e',"In which state is the Grand Canyon?",
            "California", "Texas",
            "Wyoming", "Arizona", 4
        )

        questionsList.add(que13)

        // 14
        val que14 = Question(
            14, 'm',"What state is known as the 'Land of 10,000 Lakes'?",
            "Maryland", "Kansas",
            "Minnesota", "New Jersey", 3
        )

        questionsList.add(que14)

        // 15
        val que15 = Question(
            15, 'h',"Which state does NOT border Canada?",
            "Ohio", "Michigan",
            "Montana", "Washington", 1
        )

        questionsList.add(que15)

        // 16
        val que16 = Question(
            16, 'm',"Which state does NOT contain nor border the Mississippi River?",
            "Mississippi", "Missouri",
            "Oklahoma", "Illinois", 3
        )

        questionsList.add(que16)

        // 17
        val que17 = Question(
            17, 'e',"Which is NOT a mountain range in the US?",
            "Appalachian Mountains", "Rocky Mountains",
            "Sierra Nevada", "Andes", 4
        )

        questionsList.add(que17)

        // 18
        val que18 = Question(
            18, 'm',"Which state contains the Great Salt Lake?",
            "Oregon", "Utah",
            "Florida", "Wisconsin", 2
        )

        questionsList.add(que18)

        // 19
        val que19 = Question(
            19, 'm',"In what state is Harvard University?",
            "Vermont", "Virginia",
            "Massachusetts", "New York", 3
        )

        questionsList.add(que19)

        // 20
        val que20 = Question(
            20, 'h',"What is the most populated state capital?",
            "Phoenix, AZ", "Austin, TX",
            "Richmond, VA", "Boston, MA", 1
        )

        questionsList.add(que20)



        return questionsList
    }
}
