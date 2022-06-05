package edu.ucsb.cs.cs184.trungbui.cs184project.ui.multichoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultsViewModel : ViewModel() {

    val userName = MutableLiveData<String>().apply {
        value = "username"
    }
    val resultText = MutableLiveData<String>().apply {
        value = ""
    }
    val displayLastResult = MutableLiveData<Boolean>().apply {
        value = false
    }
}