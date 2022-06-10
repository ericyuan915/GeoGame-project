# CS184 Final Project: Geo Game App
Trung Bui, Connor McIntosh, Corey Su, Dylan Sun, and Eric Yuan
 
 * For our mobile application, we created a game based upon knowledge of states in the United States. We created a game where users are tested upon their knowledge of states and rewarded for correctly guessing the state's capital, knowing the state based upon its geographical shape, or other facts and information about each state.

* The game consists of two modes: One being a multiple choice quiz game where users are asked questions about geographical or historical information about states, the other being a game integrating google maps in which the user is asked to choose which state a marker is in on the screen. These two game modes ask the user for their level of expertise and the difficulty they select determines what types of questions are asked.

* Users must sign in using Google OAuth before playing. This creates their account which is used to display their name on the leaderboard. Their profile information as well as the results of their games are saved using firebase. Each user's total career score in each game mode is saved to a leaderboard that all users can view and to challenge one another.

## App Setup
* To support the google maps game mode, the line `MAPS_API_KEY=AIzaSyA09bGOZSyx1sckpW5ZsxGzfz0Dkb_N3PU` to the local properties file in the gradle scripts
* Device API 32 should be used to support current GUI implementation
