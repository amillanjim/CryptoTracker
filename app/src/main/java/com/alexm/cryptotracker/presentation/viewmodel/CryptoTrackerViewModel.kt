package com.alexm.cryptotracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.domain.model.Coin
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
    private val getCoinsUseCase: GetCoinsUseCase,
    private val getTickerUseCase: GetTickerUseCase,
    private val getSavedCoinsUseCase: GetSavedCoinsUseCase
): ViewModel() {

    private val _cryptCoinsState = MutableStateFlow<Resource<List<Coin>>>(Resource.Empty())
    val cryptoCoinsState: StateFlow<Resource<List<Coin>>> = _cryptCoinsState

    private val _tickersState = MutableStateFlow<Resource<List<Tickers>>>(Resource.Empty())
    val tickersState: StateFlow<Resource<List<Tickers>>> = _tickersState

    private val _savedCoinsState = MutableStateFlow<Resource<List<CoinEntity>>>(Resource.Empty())
    val savedCoinsState: StateFlow<Resource<List<CoinEntity>>> = _savedCoinsState

    fun getTickers(){
        viewModelScope.launch {
            getTickerUseCase().collect{ tickers ->
                tickers.let {
                    _tickersState.value = it
                }
            }
        }
    }

    fun getCoins(){
        viewModelScope.launch {
            getCoinsUseCase().collect{ list ->
                list.let {
                    _cryptCoinsState.value = it
                }
            }
        }
    }

    fun getSavedCoins(){
        viewModelScope.launch {
            getSavedCoinsUseCase().collect{ list ->
                list.let {
                    _savedCoinsState.value = it
                }
            }
        }
    }
}