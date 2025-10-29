package com.example.quizapp.StateHandling.bashResponseState

import com.example.quizapp.data.Models.QnaResponse

sealed class BashResponseState{
    object Idle: BashResponseState()
    object Loading: BashResponseState()
    data class Success(val data: List<QnaResponse> = emptyList()): BashResponseState()
    data class Error(val error: String): BashResponseState()
}

