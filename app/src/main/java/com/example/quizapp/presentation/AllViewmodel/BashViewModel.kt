package com.example.quizapp.presentation.AllViewmodel

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.Domain.UseCases.UseCaseAccess
import com.example.quizapp.StateHandling.ApiResult
import com.example.quizapp.StateHandling.BashResponseState
import com.example.quizapp.presentation.UiIntent.UiIntent
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
class BashViewModel @Inject constructor(private val usecaseAcess: UseCaseAccess):ViewModel (){
    private val  _bashResponseState = MutableStateFlow(BashResponseState())
    val bashResponseState=_bashResponseState.asStateFlow()
    fun onIntent(intent: UiIntent){
        when(intent){
            UiIntent.BASHBUTTONCLICK->{
                getBashQuestions()
            }
            else->{

            }
        }

    }
    fun getBashQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            usecaseAcess.getBashQuestionsUseCase.getBashQuestionsUseCase().collectLatest { ApiResult ->
                withContext(Dispatchers.Main) {
                    when (ApiResult) {
                        is ApiResult.Loading -> {
                            _bashResponseState.value = BashResponseState(isLoading = true)
                        }

                        is ApiResult.Success -> {
                            _bashResponseState.value =
                                BashResponseState(isLoading = false, data = ApiResult.data)
                        }

                        is ApiResult.Error -> {
                            _bashResponseState.value =
                                BashResponseState(isLoading = false, error = ApiResult.message)
                        }
                    }
                }
            }
        }
    }
}