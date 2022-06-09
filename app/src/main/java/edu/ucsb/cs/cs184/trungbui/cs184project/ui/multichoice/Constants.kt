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

        // 0 - starting at 0 so we can access these as indices in the questions list
        val que0 = Question(
            0,'e', "In what state is Mt. Rushmore?",
            "Colorado", "South Dakota",
            "Hawaii", "New York", 2
        )
        questionsList.add(que0)

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

        // 21
        val que21 = Question(
            21, 'm',"Which of these states is the least populated?",
            "Arizona", "Wyoming",
            "Delaware", "Rhode Island", 2
        )

        questionsList.add(que21)

        // 22
        val que22 = Question(
            22, 'm',"What of these states is NOT apart of the Midwest region?",
            "North Dakota", "Kansas",
            "Arkansas", "Ohio", 3
        )

        questionsList.add(que22)

        // 23
        val que23 = Question(
            23, 'm',"What is the state flower of New York?",
            "Mayflower", "Rose",
            "Violet", "Camellia", 2
        )

        questionsList.add(que23)

        // 24
        val que24 = Question(
            24, 'h',"What is the state flower of Nevada?",
            "Sagebrush", "Goldenrod",
            "Yucca Flower", "Iris", 1
        )

        questionsList.add(que24)

        // 25
        val que25 = Question(
            25, 'h',"What is the state flower of New Jersey?",
            "Peach Blossom", "Magnolia",
            "Rose", "Violet", 4
        )

        questionsList.add(que25)

        // 26
        val que26 = Question(
            26, 'e',"In what state is Stanford University?",
            "Texas", "Washington",
            "New York", "California", 4
        )

        questionsList.add(que26)

        // 27
        val que27 = Question(
            27, 'm',"In what state is Brown University?",
            "Massachusetts", "Rhode Island",
            "New York", "Ohio", 2
        )

        questionsList.add(que27)

        // 28
        val que28 = Question(
            28, 'h',"How many electoral votes does Texas have?",
            "35", "42",
            "38", "33", 3
        )

        questionsList.add(que28)

        // 29
        val que29 = Question(
            29, 'e',"Which of these states has the most electoral votes?",
            "New York", "Texas",
            "Florida", "California", 4
        )

        questionsList.add(que29)

        // 30
        val que30 = Question(
            30, 'm',"Which state has the largest production of maple syrup?",
            "Virginia", "North Carolina",
            "Ohio", "Vermont", 4
        )

        questionsList.add(que30)

        // 31
        val que31 = Question(
            31, 'm',"What was the first state to ratify the US Constitution?",
            "Massachusetts", "Delaware",
            "Georgia", "Pennsylvania", 2
        )

        questionsList.add(que31)

        // 32
        val que32 = Question(
            32, 'e',"What state is known for lobster?",
            "Michigan", "New York",
            "California", "Maine", 4
        )

        questionsList.add(que32)

        // 33
        val que33 = Question(
            33, 'm',"Salem is the capital of which state?",
            "Washington", "Wisconsin",
            "Oregon", "Illinois", 3
        )

        questionsList.add(que33)

        // 34
        val que34 = Question(
            34, 'm',"Which state does NOT border a body of water?",
            "Louisiana", "Tennessee",
            "Michigan", "Arizona", 2
        )

        questionsList.add(que34)

        // 35
        val que35 = Question(
            35, 'm',"Which of these states borders Kentucky?",
            "Indiana", "Georgia",
            "Iowa", "South Carolina", 1
        )

        questionsList.add(que35)

        // 36
        val que36 = Question(
            36, 'h',"Which of these states does NOT have the Northern Cardinal as its state bird?",
            "Indiana", "Montana",
            "Kentucky", "Illinois", 2
        )

        questionsList.add(que36)

        // 37
        val que37 = Question(
            37, 'm',"What is the state bird of New York?",
            "Northern Cardinal", "Mountain Bluebird",
            "American Robin", "Eastern Bluebird", 4
        )

        questionsList.add(que37)

        // 38
        val que38 = Question(
            38, 'e',"What is the state bird of California?",
            "California Quail", "American Robin",
            "Brown Pelican", "Eastern Bluebird", 1
        )

        questionsList.add(que38)

        // 39
        val que39 = Question(
            39, 'm',"What is the state bird of Arizona?",
            "Brown Thrasher", "Cactus Wren",
            "Western Meadowlark", "Common Loon", 2
        )

        questionsList.add(que39)

        // 40
        val que40 = Question(
            40, 'm',"What is the state bird of New Mexico?",
            "Greater Roadrunner", "Northern Cardinal",
            "Cactus Wren", "Ruffed Grouse", 1
        )

        questionsList.add(que40)

        // 41
        val que41 = Question(
            41, 'h',"What is the state bird of Alaska?",
            "Northern Mockingbird", "Mountain Bluebird",
            "Chickadee", "Willow Ptarmigan", 4
        )

        questionsList.add(que41)

        // 42
        val que42 = Question(
            42, 'h',"What was the 43rd state added to the US?",
            "Colorado", "Texas",
            "Idaho", "Oklahoma", 3
        )

        questionsList.add(que42)

        // 43
        val que43 = Question(
            43, 'e',"How many states are there in the US?",
            "52", "50",
            "40", "55", 2
        )

        questionsList.add(que43)

        // 44
        val que44 = Question(
            44, 'm',"What was the 9th state added to the US?",
            "New Hampshire", "Rhode Island",
            "New York", "Indiana", 1
        )

        questionsList.add(que44)

        // 45
        val que45 = Question(
            45, 'e',"What was the most recent state added to the US?",
            "Washington", "Hawaii",
            "Alaska", "California", 2
        )

        questionsList.add(que45)


        // 46
        val que46 = Question(
            46, 'h',"What was the 10th state added to the US?",
            "Vermont", "Virgina",
            "Connecticut", "Kentucky", 2
        )

        questionsList.add(que46)

        // 47
        val que47 = Question(
            47, 'h',"What was the 30th state added to the US?",
            "Texas", "Wisconsin",
            "Nevada", "California", 2
        )

        questionsList.add(que47)

        // 48
        val que48 = Question(
            48, 'm',"Which president was born in California?",
            "Ronald Reagan", "Richard Nixon",
            "Jimmy Carter", "George Bush", 2
        )

        questionsList.add(que48)

        // 49
        val que49 = Question(
            49, 'm',"In what state was president Barack Obama born?",
            "Ohio", "New York",
            "Hawaii", "none of the above", 4
        )

        questionsList.add(que49)

        // 50
        val que50 = Question(
            50, 'h',"Which state gave birth to the most presidents?",
            "Washington", "Massachusetts",
            "Virginia", "Ohio", 3
        )

        questionsList.add(que50)

        // 51
        val que51 = Question(
            51, 'e',"In what state was president George Washington born?",
            "Pennsylvania", "Virginia",
            "Massachusetts", "none of the above", 4
        )

        questionsList.add(que51)

        // 52
        val que52 = Question(
            52, 'm',"In what state is Yale University located in?",
            "Connecticut", "Massachusetts",
            "Illinois", "New York", 1
        )

        questionsList.add(que52)

        // 53
        val que53 = Question(
            53, 'm',"In what state is Rice University located in?",
            "Ohio", "Texas",
            "California", "New York", 2
        )

        questionsList.add(que53)

        // 54
        val que54 = Question(
            54, 'h',"In what state is the Coca-Cola Company headquarters located in?",
            "Texas", "Illinois",
            "Georgia", "Florida", 2
        )

        questionsList.add(que54)

        // 55
        val que55 = Question(
            55, 'h',"How many states border Tennessee?",
            "5", "6",
            "7", "8", 4
        )

        questionsList.add(que55)

        // 56
        val que56 = Question(
            56, 'h',"Which of the following states does NOT border Alabama?",
            "Mississippi", "Virginia",
            "Georgia", "Tennessee", 2
        )

        questionsList.add(que56)

        // 57
        val que57 = Question(
            57, 'h',"Which is the following states borders Iowa?",
            "Nebraska", "Michigan",
            "Wyoming", "North Dakota", 1
        )

        questionsList.add(que57)

        // 58
        val que58 = Question(
            58, 'h',"Which state borders only one other state?",
            "Washington", "Rhode Island",
            "Alaska", "Maine", 4
        )

        questionsList.add(que58)

        // 59
        val que59 = Question(
            59, 'h',"Which state borders exactly 4 other states?",
            "Minnesota", "Montana",
            "Nevada", "Utah", 2
        )

        questionsList.add(que59)



        return questionsList
    }
}