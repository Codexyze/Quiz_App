package com.example.quizapp.StateHandling.linuxResponseState

import com.example.quizapp.data.Models.QnaResponse

sealed class LinuxResponseState{
    object Idle: LinuxResponseState()
    object Loading: LinuxResponseState()
    data class Success(val data: List<QnaResponse> = emptyList()): LinuxResponseState()
    data class Error(val error: String): LinuxResponseState()
}

