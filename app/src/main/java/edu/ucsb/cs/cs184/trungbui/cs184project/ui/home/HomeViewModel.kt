package edu.ucsb.cs.cs184.trungbui.cs184project.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

class HomeViewModel : ViewModel() {

    val logoutBtnStatus = MutableLiveData<Boolean>().apply {
        value = true
    }

    fun setLogoutBtnStatus(canLogout: Boolean) {
        logoutBtnStatus.value = canLogout
    }

    val loginBtnStatus = MutableLiveData<Boolean>().apply {
        value = true
    }

    fun setLoginBtnStatus(canLogin: Boolean) {
        loginBtnStatus.value = canLogin
    }

    val userStatusText = MutableLiveData<String>().apply {
        value = "No user has logged in"
    }

    fun setUserStatusText(text: String) {
        userStatusText.value = text
    }

    val firebaseUser = MutableLiveData<FirebaseUser?>().apply {
        value = FirebaseAuth.getInstance().currentUser
    }

    fun setFirebaseUser(user: FirebaseUser?) {
        firebaseUser.value = user
    }


}