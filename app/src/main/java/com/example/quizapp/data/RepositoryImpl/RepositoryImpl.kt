package com.example.quizapp.data.RepositoryImpl

import com.example.quizapp.Constants.Constants
import com.example.quizapp.Domain.RepositoryInterface.Repository
import com.example.quizapp.data.KtorClient.KtorClient
import com.example.quizapp.data.Models.QnaResponse
import com.example.quizapp.data.Models.QnaResponseItem
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// Define a sealed class for handling API states
sealed class ApiResult<out T> {
    object Loading : ApiResult<Nothing>()
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}

class RepositoryImpl : Repository {
    override suspend fun getAllQuestions(): Flow<ApiResult<List<QnaResponseItem>>> = flow {
        emit(ApiResult.Loading) // Show loading state

        try {
            val response: List<QnaResponseItem> = KtorClient.client.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("category", Constants.CATEGORY)
                parameter("limit",10)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }
    }
}