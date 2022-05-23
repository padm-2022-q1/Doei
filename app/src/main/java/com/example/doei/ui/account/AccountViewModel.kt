package com.example.doei.ui.account

import com.example.doei.domain.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doei.domain.models.Account
import com.example.doei.domain.repository.FirebaseDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val firebaseDatabaseRepository: FirebaseDatabaseRepository) : ViewModel() {

    fun handleAccountAdded(): LiveData<Boolean> = firebaseDatabaseRepository.handleAddAccount()

    fun addAccountToDataBase( account: Account){
        firebaseDatabaseRepository.addAccountToDatabase(account)
    }
}