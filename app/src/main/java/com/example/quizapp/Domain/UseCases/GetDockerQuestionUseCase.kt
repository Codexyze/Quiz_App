package com.example.quizapp.Domain.UseCases

import com.example.quizapp.Domain.RepositoryInterface.ApiResult
import com.example.quizapp.Domain.RepositoryInterface.Repository
import com.example.quizapp.data.Models.QnaResponse
import kotlinx.coroutines.flow.Flow

class GetDockerQuestionUseCase(private val repository: Repository) {
    suspend fun getDockerQuestionsUseCase(): Flow<ApiResult<List<QnaResponse>>>{
        return repository.getDockerQuestions()
    }
}
