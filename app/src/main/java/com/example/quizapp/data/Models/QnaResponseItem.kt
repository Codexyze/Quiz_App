package com.example.quizapp.data.Models

data class QnaResponseItem(
    val answers: Answers,
    val category: String,
    val correct_answer: Any,
    val correct_answers: CorrectAnswers,
    val description: String,
    val difficulty: String,
    val explanation: String,
    val id: Int,
    val multiple_correct_answers: String,
    val question: String,
    val tags: List<Tag>,
    val tip: Any
)