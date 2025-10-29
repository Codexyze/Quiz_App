package com.example.quizapp.presentation.AllViewmodel

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.Domain.RepositoryInterface.ApiResult
import com.example.quizapp.Domain.UseCases.UseCaseAccess
import com.example.quizapp.StateHandling.reactResponseState.ReactResponseState
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
class ReactQuestionsViewModel @Inject constructor(private val useCaseAcess: UseCaseAccess) : ViewModel(){
    private val _getReactQuestionstate= MutableStateFlow<ReactResponseState>(ReactResponseState.Idle)
    val getReactQuestionstate =_getReactQuestionstate.asStateFlow()
    fun getReactQuestions(){
        viewModelScope.launch(Dispatchers.IO) {
            useCaseAcess.getReactQuestionsUseCase.getReactQuestionsUseCase().collectLatest {ApiResult->
                withContext(Dispatchers.Main) {

                    when (ApiResult) {
                        is ApiResult.Loading -> {
                            _getReactQuestionstate.value = ReactResponseState.Loading
                        }

                        is ApiResult.Success -> {
                            _getReactQuestionstate.value =
                                ReactResponseState.Success(data = ApiResult.data)
                        }

                        is ApiResult.Error -> {
                            _getReactQuestionstate.value =
                                ReactResponseState.Error(error = ApiResult.message)
                        }
                    }
                }

            }
        }
    }
}