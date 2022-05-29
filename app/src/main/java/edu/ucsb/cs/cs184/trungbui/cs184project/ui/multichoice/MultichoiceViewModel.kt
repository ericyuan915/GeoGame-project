package edu.ucsb.cs.cs184.trungbui.cs184project.ui.multichoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MultichoiceViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Multichoice Fragment"
    }
    val text: LiveData<String> = _text
}