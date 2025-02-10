package com.example.quizapp.data.Models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Answers(
    val answer_a: String?="",
    val answer_b: String?="",
    val answer_c: String?="",
    val answer_d: String?="",
    val answer_e: String?="",
    val answer_f: String?=""
)