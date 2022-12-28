package com.alexm.cryptotracker.domain.use_case

import com.alexm.cryptotracker.base.BaseErrorHandler
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.data.mapper.toCoin
import com.alexm.cryptotracker.di.app.DefaultDispatcher
import com.alexm.cryptotracker.domain.model.Tickers
import com.alexm.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SaveCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
){
    operator fun invoke(tickers: Tickers, coinLogo: String): Flow<Resource<Boolean>> = flow {
        try {
            coinRepository.saveCoin(coin = tickers.toCoin(logo = coinLogo))
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                message = BaseErrorHandler.handleExceptionMessage(exception = e))
            )
        }
    }.flowOn(dispatcher)
}