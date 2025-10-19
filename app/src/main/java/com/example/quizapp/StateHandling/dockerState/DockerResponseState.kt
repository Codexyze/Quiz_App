package com.example.quizapp.StateHandling.dockerState

import com.example.quizapp.data.Models.QnaResponse

sealed class DockerResponseState{
    object Idle: DockerResponseState()
    object Loading: DockerResponseState()
    data class Success(val data: List<QnaResponse> = emptyList()): DockerResponseState()
    data class Error(val error: String): DockerResponseState()
}