package com.example.quizapp.StateHandling.apacheKafkaResponseState

import com.example.quizapp.data.Models.QnaResponse

sealed class ApacheKafkaResponseState{
    object Idle: ApacheKafkaResponseState()
    object Loading: ApacheKafkaResponseState()
    data class Success(val data: List<QnaResponse> = emptyList()): ApacheKafkaResponseState()
    data class Error(val error: String): ApacheKafkaResponseState()
}

