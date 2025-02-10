package com.example.quizapp.presentation.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.Models.QnaResponse
import com.example.quizapp.data.RepositoryImpl.ApiResult
import com.example.quizapp.data.RepositoryImpl.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository:RepositoryImpl):ViewModel () {
    private val _getAllQuestionstate= MutableStateFlow(ApiResponseState())
    val getAllQuestionstate =_getAllQuestionstate.asStateFlow()

    fun getAllQuestions(){
        viewModelScope.launch {
            repository.getAllQuestions().collectLatest {ApiResult->
                when(ApiResult){
                    is ApiResult.Loading->{
                        _getAllQuestionstate.value=ApiResponseState(isLoading = true)
                    }
                    is ApiResult.Success->{
                        _getAllQuestionstate.value=ApiResponseState(isLoading = false, data = ApiResult.data)
                    }
                    is ApiResult.Error->{
                        _getAllQuestionstate.value=ApiResponseState(isLoading = false, error = ApiResult.message)
                    }
                }

            }
        }
    }

}


data class ApiResponseState(
    val isLoading: Boolean = false,
    val data: List<QnaResponse> = emptyList(),
    val error: String = "")