package com.example.quizapp.Domain.UseCases

import com.example.quizapp.Domain.RepositoryInterface.ApiResult
import com.example.quizapp.Domain.RepositoryInterface.Repository
import com.example.quizapp.data.Models.QnaResponse
import kotlinx.coroutines.flow.Flow

class GetReactQuestionsUseCase (private val repository: Repository){
    suspend fun getReactQuestionsUseCase(): Flow<ApiResult<List<QnaResponse>>>{
        return repository.getReactQuestions()
    }
}
