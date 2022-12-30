package com.alexm.cryptotracker.domain.use_case

import com.alexm.cryptotracker.base.BaseErrorHandler
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.common.flowExceptionHandler
import com.alexm.cryptotracker.data.mapper.toTickers
import com.alexm.cryptotracker.di.app.IoDispatcher
import com.alexm.cryptotracker.domain.model.Tickers
import com.alexm.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTickersByIdUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
){
    operator fun invoke(coinId: String): Flow<Resource<Tickers>> = flow {
        emit(Resource.Loading())
        val tickers = coinRepository.getTickerById(coinId).toTickers()
        emit(Resource.Success(tickers))
    }.catch {e ->
        emit(
            Resource.Error(
                message = BaseErrorHandler.handleExceptionMessage(exception = e as Exception))
        )
    }.flowOn(dispatcher + flowExceptionHandler)
}