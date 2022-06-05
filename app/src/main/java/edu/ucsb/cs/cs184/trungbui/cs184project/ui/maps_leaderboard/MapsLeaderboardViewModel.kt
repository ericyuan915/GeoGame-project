package edu.ucsb.cs.cs184.trungbui.cs184project.ui.maps_leaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapsLeaderboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is leaderboard Fragment"
    }
    val text: LiveData<String> = _text
}