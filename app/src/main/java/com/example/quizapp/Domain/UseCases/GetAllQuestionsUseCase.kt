package com.example.quizapp.Domain.UseCases

import com.example.quizapp.Domain.RepositoryInterface.ApiResult
import com.example.quizapp.Domain.RepositoryInterface.Repository

import com.example.quizapp.data.Models.QnaResponse
import kotlinx.coroutines.flow.Flow

class GetAllQuestionsUseCase(private val repository: Repository) {
    suspend fun getAllQuestionsUseCase(): Flow<ApiResult<List<QnaResponse>>>{
        return repository.getAllQuestions()
    }
}
