package com.example.quizapp.StateHandling.getAllQuestionState

import com.example.quizapp.data.Models.QnaResponse

sealed class GetAllQuestionState{
    object Idle: GetAllQuestionState()
    object Loading: GetAllQuestionState()
    data class Success( val data: List<QnaResponse> = emptyList()): GetAllQuestionState()
    data class Error(val error: String): GetAllQuestionState()
}