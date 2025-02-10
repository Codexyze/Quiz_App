package com.example.quizapp.StateHandling

import com.example.quizapp.data.Models.QnaResponse

sealed class ApiResult<out T> {
    object Loading : ApiResult<Nothing>()
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}

data class ApiResponseState(
    val isLoading: Boolean = false,
    val data: List<QnaResponse> = emptyList(),
    val error: String = "")
data class ReactResponseState(
    val isLoading: Boolean = false,
    val data: List<QnaResponse> = emptyList(),
    val error: String = "")
data class ApacheKafkaResponseState(
    val isLoading: Boolean = false,
    val data: List<QnaResponse> = emptyList(),
    val error: String = "")