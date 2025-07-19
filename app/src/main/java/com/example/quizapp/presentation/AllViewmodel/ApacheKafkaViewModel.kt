package com.example.quizapp.presentation.AllViewmodel

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.Domain.UseCases.UseCaseAccess
import com.example.quizapp.StateHandling.ApacheKafkaResponseState
import com.example.quizapp.StateHandling.ApiResult
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
class ApacheKafkaViewModel @Inject constructor(private val usecaseAcess: UseCaseAccess):ViewModel (){
    private val _getApacheKafkaQuestionstate= MutableStateFlow(ApacheKafkaResponseState())
    val getApacheKafkaQuestionstate =_getApacheKafkaQuestionstate.asStateFlow()

    fun getApacheKafkaQuestions(){
        viewModelScope.launch(Dispatchers.IO) {

            usecaseAcess.getApacheKafkaQuestionUseCase.getApacheKafkaQuestionsUseCase().collectLatest {ApiResult->
                withContext(Dispatchers.Main) {
                    when (ApiResult) {
                        is ApiResult.Loading -> {
                            _getApacheKafkaQuestionstate.value =
                                ApacheKafkaResponseState(isLoading = true)
                        }

                        is ApiResult.Success -> {
                            _getApacheKafkaQuestionstate.value =
                                ApacheKafkaResponseState(isLoading = false, data = ApiResult.data)
                        }

                        is ApiResult.Error -> {
                            _getApacheKafkaQuestionstate.value = ApacheKafkaResponseState(
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