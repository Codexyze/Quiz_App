package com.example.quizapp.presentation.AllViewmodel

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.Domain.RepositoryInterface.ApiResult
import com.example.quizapp.Domain.UseCases.UseCaseAccess
import com.example.quizapp.StateHandling.apiResponseState.ApiResponseState
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
class PostgresViewModel @Inject constructor(private val usecaseAcess: UseCaseAccess): ViewModel(){
    private val _getAllQuestionstate= MutableStateFlow<ApiResponseState>(ApiResponseState.Idle)
    val getAllQuestionstate =_getAllQuestionstate.asStateFlow()


    fun getPostgreseQuestions(){
        viewModelScope.launch(Dispatchers.IO) {
            usecaseAcess.getPostGreseQuestionsUseCase.getPostGreseQuestionsUseCase().collectLatest {ApiResult->
                withContext(Dispatchers.Main) {
                    when (ApiResult) {
                        is ApiResult.Loading -> {
                            _getAllQuestionstate.value = ApiResponseState.Loading
                        }

                        is ApiResult.Success -> {
                            _getAllQuestionstate.value =
                                ApiResponseState.Success(data = ApiResult.data)
                        }

                        is ApiResult.Error -> {
                            _getAllQuestionstate.value =
                                ApiResponseState.Error(error = ApiResult.message)
                        }
                    }
                }

            }
        }
    }
}