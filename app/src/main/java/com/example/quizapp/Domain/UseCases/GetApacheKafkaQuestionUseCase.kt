package com.example.quizapp.Domain.UseCases

import com.example.quizapp.Domain.RepositoryInterface.Repository
import com.example.quizapp.StateHandling.ApiResult
import com.example.quizapp.data.Models.QnaResponse
import kotlinx.coroutines.flow.Flow

class GetApacheKafkaQuestionUseCase(private val repository: Repository) {
    suspend fun getApacheKafkaQuestionsUseCase(): Flow<ApiResult<List<QnaResponse>>>{
        return repository.getApacheKafkaQuestions()
    }
}
