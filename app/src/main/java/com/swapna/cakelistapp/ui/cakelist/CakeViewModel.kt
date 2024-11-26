package com.swapna.cakelistapp.ui.cakelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swapna.cakelistapp.data.model.CakeList
import com.swapna.cakelistapp.data.repository.CakeRepository
import com.swapna.cakelistapp.ui.base.UiState
import com.swapna.cakelistapp.utiles.DefaultDispatcherProvider
import com.swapna.cakelistapp.utiles.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class CakeViewModel @Inject constructor(
    private val repository: CakeRepository,
    private val dispatcherProvider: DispatcherProvider) : ViewModel(){

    private val _cakeUiState = MutableStateFlow<UiState<List<CakeList>>>(UiState.Loading)

    var cakeUiState : StateFlow<UiState<List<CakeList>>> = _cakeUiState

    init {
        fetchCakeList()
    }

    private fun fetchCakeList(){
        viewModelScope.launch(dispatcherProvider.main) {
            repository.cakeList().flowOn(dispatcherProvider.io).catch {
                _cakeUiState.value = UiState.Error(it.message.toString())
            }.collect{
                _cakeUiState.value = UiState.Success(it)
            }
        }
    }
}