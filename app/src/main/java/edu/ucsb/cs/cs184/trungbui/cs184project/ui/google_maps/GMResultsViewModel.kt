package edu.ucsb.cs.cs184.trungbui.cs184project.ui.google_maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GMResultsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is GM Results Fragment"
    }
    val text: LiveData<String> = _text
}