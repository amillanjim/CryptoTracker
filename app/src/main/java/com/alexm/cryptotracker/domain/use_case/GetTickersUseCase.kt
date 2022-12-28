package com.alexm.cryptotracker.domain.use_case

import com.alexm.cryptotracker.base.BaseErrorHandler
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.data.mapper.toTickers
import com.alexm.cryptotracker.di.app.DefaultDispatcher
import com.alexm.cryptotracker.domain.model.Tickers
import com.alexm.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
){
    operator fun invoke(): Flow<Resource<List<Tickers>>> = flow {
        try {
            emit(Resource.Loading())
            val tickers = coinRepository.getTickers().map { it.toTickers() }
            emit(Resource.Success(tickers))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = BaseErrorHandler.handleExceptionMessage(exception = e))
            )
        }
    }.flowOn(dispatcher)
}