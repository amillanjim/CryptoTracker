package com.alexm.cryptotracker.domain.use_case

import com.alexm.cryptotracker.base.BaseErrorHandler
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.common.flowExceptionHandler
import com.alexm.cryptotracker.data.mapper.toCoin
import com.alexm.cryptotracker.di.app.IoDispatcher
import com.alexm.cryptotracker.domain.model.Tickers
import com.alexm.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import java.sql.SQLException
import javax.inject.Inject

class SaveCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
){
    operator fun invoke(tickers: Tickers, coinLogo: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Empty())
        coinRepository.saveCoin(coin = tickers.toCoin(logo = coinLogo))
        emit(Resource.Success(true))
    }.catch {e ->
        emit(
            Resource.Error(
                message = BaseErrorHandler.handleExceptionMessage(exception = e as Exception))
        )
    }.retryWhen{ cause, attempt -> cause is SQLException || attempt < 2
    }.flowOn(dispatcher + flowExceptionHandler)
}