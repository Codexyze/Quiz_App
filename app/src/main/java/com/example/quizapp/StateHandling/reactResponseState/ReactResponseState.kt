package com.example.quizapp.StateHandling.reactResponseState

import com.example.quizapp.data.Models.QnaResponse

sealed class ReactResponseState{
    object Idle: ReactResponseState()
    object Loading: ReactResponseState()
    data class Success(val data: List<QnaResponse> = emptyList()): ReactResponseState()
    data class Error(val error: String): ReactResponseState()
}

