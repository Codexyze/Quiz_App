package com.example.quizapp.Domain.UseCases

import com.example.quizapp.Domain.RepositoryInterface.Repository
import com.example.quizapp.StateHandling.ApiResult
import com.example.quizapp.data.Models.QnaResponse
import kotlinx.coroutines.flow.Flow

class GetBashQuestionsUseCase(private val repository: Repository) {
    suspend fun getBashQuestionsUseCase(): Flow<ApiResult<List<QnaResponse>>>{
        return repository.getBashQuestions()
    }
}
