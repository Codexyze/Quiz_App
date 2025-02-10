package com.example.quizapp.data.Models
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class QnaResponse(
    val answers: Answers?,
    val category: String?="",
    val correct_answer: String?="",
    val correct_answers: CorrectAnswers?,
    val description: String?="",
    val difficulty: String?="",
    val explanation: String?="",
    val id: Int?,
    val multiple_correct_answers: String?="",
    val question: String?="",
    val tags: List<Tag>,
    val tip:String?=""
)