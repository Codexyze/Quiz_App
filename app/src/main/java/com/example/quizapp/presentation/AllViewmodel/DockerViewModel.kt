package com.example.quizapp.presentation.AllViewmodel

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.Domain.UseCases.UseCaseAccess
import com.example.quizapp.StateHandling.ApiResult
import com.example.quizapp.StateHandling.DockerResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@Keep
@HiltViewModel
class DockerViewModel @Inject constructor(private val useCaseAccess: UseCaseAccess) : ViewModel(){
    private val _getDockerQuestionState = MutableStateFlow(DockerResponseState())
    val getDockerQuestionState=_getDockerQuestionState.asStateFlow()


    fun getDockerQuestions() {
        viewModelScope.launch {
            useCaseAccess.getDockerQuestionUseCase.getDockerQuestionsUseCase().collectLatest { ApiResult ->
                when (ApiResult) {
                    is ApiResult.Loading -> {
                        _getDockerQuestionState.value = DockerResponseState(isLoading = true)
                    }

                    is ApiResult.Success -> {
                        _getDockerQuestionState.value =
                            DockerResponseState(isLoading = false, data = ApiResult.data)
                    }                    is ApiResult.Error -> {
                    _getDockerQuestionState.value =
                        DockerResponseState(isLoading = false, error = ApiResult.message)
                }
                }
            }
        }
    }

}