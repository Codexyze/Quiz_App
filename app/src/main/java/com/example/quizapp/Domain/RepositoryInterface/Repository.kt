package com.example.quizapp.Domain.RepositoryInterface

import com.example.quizapp.data.Models.QnaResponse
import com.example.quizapp.data.Models.QnaResponseItem
import com.example.quizapp.data.RepositoryImpl.ApiResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getAllQuestions(): Flow<ApiResult<List<QnaResponseItem>>>
}