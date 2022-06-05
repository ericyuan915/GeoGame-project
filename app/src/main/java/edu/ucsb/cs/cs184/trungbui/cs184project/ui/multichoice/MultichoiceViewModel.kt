package edu.ucsb.cs.cs184.trungbui.cs184project.ui.multichoice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MultichoiceViewModel : ViewModel() {
    val totalQuestions = MutableLiveData<String>().apply {
        value = ""
    }
    val correctAnswers = MutableLiveData<String>().apply {
        value = ""
    }
}