package com.example.quizapp.presentation.Viewmodel

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.Domain.UseCases.UseCaseAccess
import com.example.quizapp.StateHandling.ApiResult
import com.example.quizapp.StateHandling.LinuxResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@Keep
@HiltViewModel
class LinuxViewModel @Inject constructor(private val usecaseAcess: UseCaseAccess):ViewModel (){
    private val _getLinuxResponseState= MutableStateFlow(LinuxResponseState())
    val getLinuxResponseState=_getLinuxResponseState.asStateFlow()
    fun getLinuxQuestions() {
        viewModelScope.launch {
            usecaseAcess.getLinuxQuestionUseCase.getLinuxQuestionsUseCase().collectLatest { ApiResult ->
                when (ApiResult) {
                    is ApiResult.Loading -> {
                        _getLinuxResponseState.value = LinuxResponseState(isLoading = true)
                    }

                    is ApiResult.Success -> {
                        _getLinuxResponseState.value =
                            LinuxResponseState(isLoading = false, data = ApiResult.data)
                    }

                    is ApiResult.Error -> {
                        _getLinuxResponseState.value =
                            LinuxResponseState(isLoading = false, error = ApiResult.message)
                    }
                }
            }
        }
    }
}