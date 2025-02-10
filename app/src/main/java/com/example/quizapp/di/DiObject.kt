package com.example.quizapp.di

import com.example.quizapp.data.RepositoryImpl.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DiObject {

@Provides
fun giveRepoInstance():RepositoryImpl{
    return RepositoryImpl()
}

}