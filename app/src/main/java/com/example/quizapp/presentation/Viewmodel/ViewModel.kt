package com.example.quizapp.presentation.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.StateHandling.ApiResponseState
import com.example.quizapp.StateHandling.ApiResult
import com.example.quizapp.StateHandling.ReactResponseState
import com.example.quizapp.data.Models.QnaResponse
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
    private val _getReactQuestionstate= MutableStateFlow(ReactResponseState())
    val getReactQuestionstate =_getReactQuestionstate.asStateFlow()

    fun getPostgreseQuestions(){
        viewModelScope.launch {
            repository.getPostgreseQuestions().collectLatest {ApiResult->
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

    fun getReactQuestions(){
        viewModelScope.launch {
            repository.getReactQuestions().collectLatest {ApiResult->
                when(ApiResult){
                    is ApiResult.Loading->{
                        _getReactQuestionstate.value=ReactResponseState(isLoading = true)
                    }
                    is ApiResult.Success->{
                        _getReactQuestionstate.value=ReactResponseState(isLoading = false, data = ApiResult.data)
                    }
                    is ApiResult.Error->{
                        _getReactQuestionstate.value=ReactResponseState(isLoading = false, error = ApiResult.message)
                    }
                }

            }
        }
    }

}

