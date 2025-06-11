package com.example.quizapp.presentation.Viewmodel

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.Domain.UseCases.UseCaseAccess
import com.example.quizapp.StateHandling.ApiResponseState
import com.example.quizapp.StateHandling.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@Keep
@HiltViewModel
class PostgresViewModel @Inject constructor(private val usecaseAcess: UseCaseAccess): ViewModel(){
    private val _getAllQuestionstate= MutableStateFlow(ApiResponseState())
    val getAllQuestionstate =_getAllQuestionstate.asStateFlow()


    fun getPostgreseQuestions(){
        viewModelScope.launch {
            usecaseAcess.getPostGreseQuestionsUseCase.getPostGreseQuestionsUseCase().collectLatest {ApiResult->
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