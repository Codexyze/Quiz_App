package com.example.quizapp.data.RepositoryImpl

import com.example.quizapp.Constants.Constants
import com.example.quizapp.Domain.RepositoryInterface.ApiResult
import com.example.quizapp.Domain.RepositoryInterface.Repository

import com.example.quizapp.data.Models.QnaResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation of the Repository interface that provides quiz questions from various technology categories.
 *
 * This repository handles API calls to fetch quiz questions and manages the response states using Flow and ApiResult.
 * It uses Ktor HttpClient for network operations and implements dependency injection with Hilt.
 *
 * @property httpClient The HTTP client used for making API requests
 *
 * @constructor Creates a RepositoryImpl instance with the provided HttpClient
 * @param httpClient The Ktor HttpClient instance injected by Hilt
 *
 * @author Quiz App Team
 * @since 1.0
 */
class RepositoryImpl @Inject constructor(private val httpClient: HttpClient) : Repository {

    /**
     * Fetches PostgreSQL-related quiz questions from the API.
     *
     * @return Flow<ApiResult<List<QnaResponse>>> A flow that emits the API result states (Loading, Success, Error)
     * containing a list of PostgreSQL quiz questions
     *
     * @throws Exception If there's an error during the API call
     */
    override suspend fun getPostgreseQuestions(): Flow<ApiResult<List<QnaResponse>>> = flow {
        emit(ApiResult.Loading) // Show loading state

        try {
            val response: List<QnaResponse> = httpClient.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("category", Constants.CATEGORY)
                parameter("limit", 10)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }
    }

    /**
     * Fetches React-related quiz questions from the API.
     *
     * @return Flow<ApiResult<List<QnaResponse>>> A flow that emits the API result states (Loading, Success, Error)
     * containing a list of React quiz questions
     *
     * @throws Exception If there's an error during the API call
     */
    override suspend fun getReactQuestions(): Flow<ApiResult<List<QnaResponse>>> = flow {
        emit(ApiResult.Loading) // Show loading state

        try {
            val response: List<QnaResponse> = httpClient.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("category", Constants.REACT)
                parameter("limit", 10)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }
    }

    /**
     * Fetches Apache Kafka-related quiz questions from the API.
     *
     * Note: Currently using Constants.REACT instead of Constants.APACHE_KAFKA - this appears to be a bug
     * that should be addressed in the Constants class.
     *
     * @return Flow<ApiResult<List<QnaResponse>>> A flow that emits the API result states (Loading, Success, Error)
     * containing a list of Apache Kafka quiz questions
     *
     * @throws Exception If there's an error during the API call
     */
    override suspend fun getApacheKafkaQuestions(): Flow<ApiResult<List<QnaResponse>>> = flow {
        emit(ApiResult.Loading) // Show loading state

        try {
            val response: List<QnaResponse> = httpClient.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("category", Constants.REACT)
                parameter("limit", 10)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }
    }

    /**
     * Fetches Bash scripting-related quiz questions from the API.
     *
     * @return Flow<ApiResult<List<QnaResponse>>> A flow that emits the API result states (Loading, Success, Error)
     * containing a list of Bash quiz questions
     *
     * @throws Exception If there's an error during the API call
     */
    override suspend fun getBashQuestions(): Flow<ApiResult<List<QnaResponse>>> = flow {
        emit(ApiResult.Loading)
        try {
            val response: List<QnaResponse> = httpClient.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("category", Constants.BASH)
                parameter("limit", 10)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }
    }

    /**
     * Fetches Linux-related quiz questions from the API.
     *
     * @return Flow<ApiResult<List<QnaResponse>>> A flow that emits the API result states (Loading, Success, Error)
     * containing a list of Linux quiz questions
     *
     * @throws Exception If there's an error during the API call
     */
    override suspend fun getLinuxQuestions(): Flow<ApiResult<List<QnaResponse>>> = flow {
        emit(ApiResult.Loading)

        try {
            val response: List<QnaResponse> = httpClient.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("category", Constants.LINUX)
                parameter("limit", 10)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }
    }

    /**
     * Fetches Docker-related quiz questions from the API.
     *
     * @return Flow<ApiResult<List<QnaResponse>>> A flow that emits the API result states (Loading, Success, Error)
     * containing a list of Docker quiz questions
     *
     * @throws Exception If there's an error during the API call
     */
    override suspend fun getDockerQuestions(): Flow<ApiResult<List<QnaResponse>>> = flow {
        emit(ApiResult.Loading)
        try {
            val response: List<QnaResponse> = httpClient.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("category", Constants.DOCKER)
                parameter("limit", 10)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }
    }

    /**
     * Fetches all available quiz questions from the API without category filtering.
     *
     * This method retrieves a mixed set of questions from all categories, limited to 15 questions.
     *
     * @return Flow<ApiResult<List<QnaResponse>>> A flow that emits the API result states (Loading, Success, Error)
     * containing a list of mixed quiz questions from all categories
     *
     * @throws Exception If there's an error during the API call
     */
    override suspend fun getAllQuestions(): Flow<ApiResult<List<QnaResponse>>> = flow {
        emit(ApiResult.Loading)
        try {
            val response: List<QnaResponse> = httpClient.get(Constants.BASEURL) {
                parameter("apiKey", Constants.APIKEY)
                parameter("limit", 15)
            }.body()
            emit(ApiResult.Success(response)) // Success case
        } catch (e: Exception) {
            emit(ApiResult.Error("Error: ${e.message}")) // Error case
        }
    }
}