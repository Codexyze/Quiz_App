package com.example.quizapp.presentation.AllViewmodel

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.Domain.RepositoryInterface.ApiResult
import com.example.quizapp.Domain.UseCases.UseCaseAccess
import com.example.quizapp.StateHandling.getAllQuestionState.GetAllQuestionState
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
    private val _getAllQuestionState = MutableStateFlow<GetAllQuestionState>(GetAllQuestionState.Idle)
    val getAllQuestionState=_getAllQuestionState.asStateFlow()

    fun getAllQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseAccess.getAllQuestionsUseCase.getAllQuestionsUseCase().collectLatest { ApiResult ->
                withContext(Dispatchers.Main) {
                    when (ApiResult) {
                        is ApiResult.Loading -> {
                            _getAllQuestionState.value =
                                GetAllQuestionState.Loading
                        }

                        is ApiResult.Success -> {
                            _getAllQuestionState.value =
                                GetAllQuestionState.Success(
                                    data = ApiResult.data
                                )
                        }

                        is ApiResult.Error -> {
                            _getAllQuestionState.value =
                                GetAllQuestionState.Error(
                                    error = ApiResult.message
                                )
                        }
                    }
                }
            }
        }
    }
}