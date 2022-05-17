package com.example.doei.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.doei.domain.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val firebaseAuthRepository: FirebaseAuthRepository) : ViewModel() {

    private val firebaseUser = firebaseAuthRepository.getCurrentUser
    private val authError = firebaseAuthRepository.authError

    fun getCurrentUser(): LiveData<FirebaseUser?> = firebaseUser

    fun handleAuthError(): LiveData<String?> = authError

    fun loginClicked(email: String, password: String) {
        firebaseAuthRepository.login(email, password)
    }

}