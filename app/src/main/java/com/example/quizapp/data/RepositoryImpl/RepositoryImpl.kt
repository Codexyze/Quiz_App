package com.example.quizapp.data.RepositoryImpl

import com.example.quizapp.Constants.Constants
import com.example.quizapp.Domain.RepositoryInterface.Repository
import com.example.quizapp.StateHandling.ApiResult
import com.example.quizapp.data.Models.QnaResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

// Define a sealed class for handling API states


class RepositoryImpl  @Inject constructor(private val httpClient: HttpClient): Repository {
    override suspend fun getPostgreseQuestions(): Flow<ApiResult<List<QnaResponse>>> = flow {
        emit(ApiResult.Loading) // Show loading state

        try {
            val response:List< QnaResponse> = httpClient.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("category", Constants.CATEGORY)
                parameter("limit",10)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }
    }

    override suspend fun getReactQuestions(): Flow<ApiResult<List<QnaResponse>>> =flow {
        emit(ApiResult.Loading) // Show loading state

        try {
            val response:List< QnaResponse> = httpClient.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("category", Constants.REACT)
                parameter("limit",10)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }
    }

    override suspend fun getApacheKafkaQuestions(): Flow<ApiResult<List<QnaResponse>>> =flow{
        emit(ApiResult.Loading) // Show loading state

        try {
            val response:List< QnaResponse> =httpClient.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("category", Constants.REACT)
                parameter("limit",10)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }
    }

    override suspend fun getBashQuestions(): Flow<ApiResult<List<QnaResponse>>> =flow {
        emit(ApiResult.Loading)
       try {
           val response:List< QnaResponse> = httpClient.get(Constants.BASEURL) {
               parameter("apiKey", Constants.APIKEY)
               parameter("category", Constants.BASH)
               parameter("limit",10)
           }.body()
           emit(ApiResult.Success(response)) // Success case
       } catch (e: Exception) {
           emit(ApiResult.Error("Error: ${e.message}")) // Error case
       }
    }

    override suspend fun getLinuxQuestions(): Flow<ApiResult<List<QnaResponse>>> = flow{
        emit(ApiResult.Loading)

        try {
            val response:List< QnaResponse> = httpClient.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("category", Constants.LINUX)
                parameter("limit",10)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }


    }

    override suspend fun getDockerQuestions(): Flow<ApiResult<List<QnaResponse>>> =flow {
        emit(ApiResult.Loading)
        try {
            val response:List< QnaResponse> = httpClient.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("category", Constants.DOCKER)
                parameter("limit",10)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }

    }

    override suspend fun getAllQuestions(): Flow<ApiResult<List<QnaResponse>>> =flow{
        emit(ApiResult.Loading)
        try {
            val response:List< QnaResponse> = httpClient.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("limit",15)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }
    }
}