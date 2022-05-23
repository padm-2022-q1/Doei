package com.example.doei.domain.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserInfo
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(private val auth: FirebaseAuth) {

    val authError: MutableLiveData<String?> = MutableLiveData()
    val getCurrentUser: MutableLiveData<FirebaseUser?> = MutableLiveData()


    init {
        if (auth.currentUser != null) {
            getCurrentUser.postValue(auth.currentUser)
        }
    }

    fun register(email: String?, pass: String?) {
        auth.createUserWithEmailAndPassword(email!!, pass!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                getCurrentUser.postValue(auth.currentUser)
            } else {
                authError.postValue(task.exception?.message)
            }
        }
    }

    fun login(email: String?, pass: String?) {
        auth.signInWithEmailAndPassword(email!!, pass!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                getCurrentUser.postValue(auth.currentUser)
            } else {
                authError.postValue(task.exception?.message)
            }
        }
    }

    fun signOut() {
        auth.signOut()
    }

    private fun getUser(): String = getCurrentUser.value?.uid
        ?: throw Exception("No user is signed in")

    val userId = getUser()


}