package com.example.quizapp.presentation.Viewmodel

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.Domain.UseCases.UseCaseAccess
import com.example.quizapp.StateHandling.ApiResult
import com.example.quizapp.StateHandling.ReactResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@Keep
@HiltViewModel
class ReactQuestionsViewModel @Inject constructor(private val useCaseAcess: UseCaseAccess) : ViewModel(){
    private val _getReactQuestionstate= MutableStateFlow(ReactResponseState())
    val getReactQuestionstate =_getReactQuestionstate.asStateFlow()
    fun getReactQuestions(){
        viewModelScope.launch {
            useCaseAcess.getReactQuestionsUseCase.getReactQuestionsUseCase().collectLatest {ApiResult->
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