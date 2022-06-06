package edu.ucsb.cs.cs184.trungbui.cs184project.ui.multichoice_leaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MultichoiceLeaderboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is leaderboard Fragment"
    }
    val text: LiveData<String> = _text
}