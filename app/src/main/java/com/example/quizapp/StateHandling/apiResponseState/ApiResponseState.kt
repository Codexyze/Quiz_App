package com.example.quizapp.StateHandling.apiResponseState

import com.example.quizapp.data.Models.QnaResponse

sealed class ApiResponseState{
    object Idle: ApiResponseState()
    object Loading: ApiResponseState()
    data class Success(val data: List<QnaResponse> = emptyList()): ApiResponseState()
    data class Error(val error: String): ApiResponseState()
}

