package com.alexm.cryptotracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.domain.model.CoinDetail
import com.alexm.cryptotracker.domain.model.Tickers
import com.alexm.cryptotracker.domain.use_case.GetCoinByIdUseCase
import com.alexm.cryptotracker.domain.use_case.GetSavedCoinByNameUseCase
import com.alexm.cryptotracker.domain.use_case.GetTickersByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    private val getTickersByIdUseCase: GetTickersByIdUseCase,
    private val getSavedCoinByNameUseCase: GetSavedCoinByNameUseCase
): ViewModel() {

    private val _coinDetailState = MutableStateFlow<Resource<CoinDetail>>(Resource.Empty())
    val coinDetailState: StateFlow<Resource<CoinDetail>> = _coinDetailState

    private val _savedCoinDetailState =
        MutableStateFlow<Resource<CoinEntity>>(Resource.Empty())
    val savedCoinDetailState: StateFlow<Resource<CoinEntity>> = _savedCoinDetailState

    private val _tickersState = MutableStateFlow<Resource<Tickers>>(Resource.Empty())
    val tickersState: StateFlow<Resource<Tickers>> = _tickersState

    private val _coinDetailHasFinished = MutableStateFlow(false)
    val coinDetailHasFinished: StateFlow<Boolean> = _coinDetailHasFinished

    private val _tickersHasFinished = MutableStateFlow(false)
    val tickersHasFinished: StateFlow<Boolean> = _tickersHasFinished

    fun getTickers(coinId: String){
        _tickersHasFinished.value = false
        viewModelScope.launch {
            getTickersByIdUseCase(coinId = coinId).collect {
                _tickersHasFinished.value = true
                _tickersState.value = it
            }
        }
    }

    fun getCoinDetail(coinId: String){
        _coinDetailHasFinished.value = false
        viewModelScope.launch {
            getCoinByIdUseCase(coinId = coinId).collect {
                _coinDetailHasFinished.value = true
                _coinDetailState.value = it
            }
        }
    }

    fun getSavedCoinDetail(coinName: String) {
        viewModelScope.launch {
            getSavedCoinByNameUseCase(coinName = coinName).collect {
                _savedCoinDetailState.value = it
            }
        }
    }
}