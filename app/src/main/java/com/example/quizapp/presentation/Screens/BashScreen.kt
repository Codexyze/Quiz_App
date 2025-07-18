package com.example.quizapp.presentation.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizapp.Constants.TestTags
import com.example.quizapp.presentation.AllViewmodel.BashViewModel



@Composable
fun GetBashQuestionScreen(viewModel: BashViewModel = hiltViewModel()) {
    val state = viewModel.bashResponseState.collectAsState()
    var score by rememberSaveable { mutableStateOf(0) } // Score counter

    LaunchedEffect(Unit) {
        viewModel.getBashQuestions()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Score Counter Display
        Text(
            text = "Score: $score /10",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50),
            modifier = Modifier.padding(bottom = 16.dp).testTag(TestTags.BASH_SCORE)
        )

        if (state.value.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (state.value.error.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error loading data", color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed (state.value.data ?: emptyList()) {index, questionItem ->
                    var selectedAnswer by remember { mutableStateOf<String?>(null) }
                    var isCorrect by remember { mutableStateOf<Boolean?>(null) }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(6.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = questionItem.question.toString(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = if (index == 0) Modifier.testTag(TestTags.BASH_SCROLL)else Modifier
                            )

                            // Display options
                            listOf(
                                "A" to questionItem.answers?.answer_a,
                                "B" to questionItem.answers?.answer_b,
                                "C" to questionItem.answers?.answer_c,
                                "D" to questionItem.answers?.answer_d
                            ).forEach { (option, answer) ->
                                answer?.let {
                                    Button(
                                        onClick = {
                                            if (selectedAnswer == null) { // Prevent multiple selections
                                                selectedAnswer = option
                                                val correct = when (option) {
                                                    "A" -> questionItem.correct_answers?.answer_a_correct.toBoolean()
                                                    "B" -> questionItem.correct_answers?.answer_b_correct.toBoolean()
                                                    "C" -> questionItem.correct_answers?.answer_c_correct.toBoolean()
                                                    "D" -> questionItem.correct_answers?.answer_d_correct.toBoolean()
                                                    else -> false
                                                }
                                                isCorrect = correct
                                                if (correct) {
                                                    score += 1 // Increase score if correct
                                                }
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = if (selectedAnswer == option) {
                                                if (isCorrect == true) Color(0xFF4CAF50) else Color(0xFFF44336)
                                            } else Color(0xFF1976D2)
                                        ),
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(
                                            text = "Option $option: $answer",
                                            color = Color.White,
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }

                            // Show result text
                            isCorrect?.let {
                                Text(
                                    text = if (it) "Correct Answer!" else "Wrong Answer!",
                                    color = if (it) Color(0xFF4CAF50) else Color(0xFFF44336),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}