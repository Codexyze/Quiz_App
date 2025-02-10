package com.example.quizapp.presentation.Screens

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizapp.presentation.Viewmodel.ViewModel
import io.ktor.websocket.Frame

@Composable
fun GetAllQuestionScreen(viewModel: ViewModel= hiltViewModel()){
    val state=viewModel.getAllQuestionstate.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getAllQuestions()
    }
    if(state.value.isLoading){
        CircularProgressIndicator()
    }
    else if(state.value.error!=""){

        Text("Error in loading")
        Log.d("TAGGG","${state.value.error}")
    }else {

            LazyColumn {
                items(state.value.data ?: emptyList()) { questionItem ->
                    Text(text = questionItem.question.toString()) // Displaying question text as an example
                    Text("${questionItem.answers?.answer_a}")
                    Text("${questionItem.answers?.answer_b}")
                    Text("${questionItem.answers?.answer_c}")
                    Text("${questionItem.answers?.answer_d}")
                }
            }

    }

}