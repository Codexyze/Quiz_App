package com.example.quizapp.Domain.RepositoryInterface

import com.example.quizapp.StateHandling.ApiResult
import com.example.quizapp.data.Models.QnaResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getPostgreseQuestions(): Flow<ApiResult<List<QnaResponse>>>
    suspend fun getReactQuestions(): Flow<ApiResult<List<QnaResponse>>>
    suspend fun getApacheKafkaQuestions(): Flow<ApiResult<List<QnaResponse>>>
    suspend fun getBashQuestions(): Flow<ApiResult<List<QnaResponse>>>
    suspend fun getLinuxQuestions(): Flow<ApiResult<List<QnaResponse>>>

}