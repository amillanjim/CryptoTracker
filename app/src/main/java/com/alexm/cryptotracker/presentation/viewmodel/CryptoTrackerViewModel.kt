package com.alexm.cryptotracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.domain.model.Tickers
import com.alexm.cryptotracker.domain.use_case.GetCoinsUseCase
import com.alexm.cryptotracker.domain.use_case.GetSavedCoinsUseCase
import com.alexm.cryptotracker.domain.use_case.GetTickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoTrackerViewModel @Inject constructor(
    private val getTickerUseCase: GetTickerUseCase,
    private val getSavedCoinsUseCase: GetSavedCoinsUseCase
): ViewModel() {

    private val _tickersState = MutableStateFlow<Resource<List<Tickers>>>(Resource.Empty())
    val tickersState: StateFlow<Resource<List<Tickers>>> = _tickersState

    private val _savedCoinsState = MutableStateFlow<Resource<List<CoinEntity>>>(Resource.Empty())
    val savedCoinsState: StateFlow<Resource<List<CoinEntity>>> = _savedCoinsState

    private val _savedCoinsHasFinished = MutableStateFlow(false)
    val savedCoinsHasFinished: StateFlow<Boolean> = _savedCoinsHasFinished

    private val _tickersHasFinished = MutableStateFlow(false)
    val tickersHasFinished: StateFlow<Boolean> = _tickersHasFinished

    fun getTickers(){
        _tickersHasFinished.value = false
        viewModelScope.launch {
            getTickerUseCase().collect{ tickers ->
                tickers.let {
                    _tickersState.value = it
                    _tickersHasFinished.value = true
                }
            }
        }
    }

    fun getSavedCoins(){
        _savedCoinsHasFinished.value = false
        viewModelScope.launch {
            getSavedCoinsUseCase().collect{ list ->
                list.let {
                    _savedCoinsState.value = it
                    _savedCoinsHasFinished.value = true
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        _tickersHasFinished.value = false
        _savedCoinsHasFinished.value = false
    }
}