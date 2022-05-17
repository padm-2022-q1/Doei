package com.example.doei.domain.di

import com.example.doei.domain.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    fun getFirebaseAuth() = FirebaseAuth.getInstance()
}