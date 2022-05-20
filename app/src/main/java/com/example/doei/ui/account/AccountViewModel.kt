package com.example.doei.ui.account

import com.example.doei.domain.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doei.domain.models.Account
import com.example.doei.domain.repository.FirebaseDatabaseRepository
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val firebaseDatabaseRepository: FirebaseDatabaseRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is LOGIN Fragment"
    }
    val text: LiveData<String> = _text

    fun addAccountInfosToDataBase( account: Account): Boolean{

        return firebaseDatabaseRepository.updateAccountInfo(account)


    }
}