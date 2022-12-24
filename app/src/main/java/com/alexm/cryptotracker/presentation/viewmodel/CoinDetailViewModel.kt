package com.alexm.cryptotracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.domain.model.CoinDetail
import com.alexm.cryptotracker.domain.model.Tickers
import com.alexm.cryptotracker.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val saveCoinUseCase: SaveCoinUseCase,
    private val deleteCoinUseCase: DeleteCoinUseCase,
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

    private val _saveCoinState = MutableStateFlow<Resource<Boolean>>(Resource.Empty())
    val saveCoinState: StateFlow<Resource<Boolean>> = _saveCoinState

    private val _deleteCoinState = MutableStateFlow<Resource<Boolean>>(Resource.Empty())
    val deleteCoinState: StateFlow<Resource<Boolean>> = _deleteCoinState

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

    fun getSavedCoinDetail(coinId: String) {
        viewModelScope.launch {
            getSavedCoinByNameUseCase(coinId = coinId).collect {
                _savedCoinDetailState.value = it
            }
        }
    }

    fun saveCoin(tickers: Tickers, coinLogo: String){
        viewModelScope.launch {
            saveCoinUseCase(tickers = tickers, coinLogo = coinLogo).collect{
                _saveCoinState.value = it
            }
        }
    }

    fun deleteCoin(coin: CoinEntity? = null){
        viewModelScope.launch {
            deleteCoinUseCase(coin = coin).collect{
                _deleteCoinState.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        _coinDetailHasFinished.value = false
        _tickersHasFinished.value = false
    }
}