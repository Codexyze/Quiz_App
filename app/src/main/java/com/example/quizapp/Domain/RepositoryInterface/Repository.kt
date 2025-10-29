package com.example.quizapp.Domain.RepositoryInterface

import com.example.quizapp.data.Models.QnaResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getPostgreseQuestions(): Flow<ApiResult<List<QnaResponse>>>
    suspend fun getReactQuestions(): Flow<ApiResult<List<QnaResponse>>>
    suspend fun getApacheKafkaQuestions(): Flow<ApiResult<List<QnaResponse>>>
    suspend fun getBashQuestions(): Flow<ApiResult<List<QnaResponse>>>
    suspend fun getLinuxQuestions(): Flow<ApiResult<List<QnaResponse>>>
    suspend fun getDockerQuestions(): Flow<ApiResult<List<QnaResponse>>>
    suspend fun getAllQuestions(): Flow<ApiResult<List<QnaResponse>>>

}

sealed class ApiResult<out T>{
    object Loading: ApiResult<Nothing>()
    data class Success<T>(val data: T): ApiResult<T>()
    data class Error(val message: String): ApiResult<Nothing>()
}
