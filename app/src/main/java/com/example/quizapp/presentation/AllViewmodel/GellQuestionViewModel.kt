package com.example.quizapp.presentation.AllViewmodel

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.Domain.UseCases.UseCaseAccess
import com.example.quizapp.StateHandling.ApiResult
import com.example.quizapp.StateHandling.GetAllQuestionResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Keep
@HiltViewModel
class GetAllQuestionViewModel @Inject constructor(private val useCaseAccess: UseCaseAccess) : ViewModel(){
    private val _getAllQuestionState = MutableStateFlow(GetAllQuestionResponseState())
    val getAllQuestionState=_getAllQuestionState.asStateFlow()

    fun getAllQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseAccess.getAllQuestionsUseCase.getAllQuestionsUseCase().collectLatest { ApiResult ->
                withContext(Dispatchers.Main) {
                    when (ApiResult) {
                        is ApiResult.Loading -> {
                            _getAllQuestionState.value =
                                GetAllQuestionResponseState(isLoading = true)
                        }

                        is ApiResult.Success -> {
                            _getAllQuestionState.value =
                                GetAllQuestionResponseState(
                                    isLoading = false,
                                    data = ApiResult.data
                                )
                        }

                        is ApiResult.Error -> {
                            _getAllQuestionState.value =
                                GetAllQuestionResponseState(
                                    isLoading = false,
                                    error = ApiResult.message
                                )
                        }
                    }
                }
            }
        }
    }
}