package com.example.quizapp.data.Models
import kotlinx.serialization.Serializable

@Serializable
data class CorrectAnswers(
    val answer_a_correct: String?="",
    val answer_b_correct: String?="",
    val answer_c_correct: String?="",
    val answer_d_correct: String?="",
    val answer_e_correct: String?="",
    val answer_f_correct: String?=""
)