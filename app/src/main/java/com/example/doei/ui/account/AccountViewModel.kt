package com.example.doei.ui.account

import com.example.doei.domain.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val firebaseAuthRepository: FirebaseAuthRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is LOGIN Fragment"
    }
    val text: LiveData<String> = _text

    fun saveAccountInfos( name: String?, idade: String?, email: String?){

        firebaseAuthRepository.updateAccountInfo(name, idade, email)


    }
}