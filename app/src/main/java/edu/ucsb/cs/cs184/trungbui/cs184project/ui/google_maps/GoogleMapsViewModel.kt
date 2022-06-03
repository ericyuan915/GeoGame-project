package edu.ucsb.cs.cs184.trungbui.cs184project.ui.google_maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GoogleMapsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is google maps fragment"
    }
    val text: LiveData<String> = _text

    public val userName = MutableLiveData<String>().apply {
        value = "username"
    }
    public val totalQuestions = MutableLiveData<String>().apply {
        value = ""
    }
    public val correctAnswers = MutableLiveData<String>().apply {
        value = ""
    }
}