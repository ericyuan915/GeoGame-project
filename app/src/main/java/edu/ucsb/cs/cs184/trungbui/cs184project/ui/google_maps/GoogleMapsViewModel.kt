package edu.ucsb.cs.cs184.trungbui.cs184project.ui.google_maps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GoogleMapsViewModel : ViewModel() {

    val totalQuestions = MutableLiveData<String>().apply {
        value = ""
        }

    val correctAnswers = MutableLiveData<String>().apply {
        value = ""
    }
}