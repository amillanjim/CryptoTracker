package com.alexm.cryptotracker.domain.use_case

import com.alexm.cryptotracker.di.app.DefaultDispatcher
import com.alexm.cryptotracker.common.ErrorHandler
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.data.mapper.toCoinDetail
import com.alexm.cryptotracker.domain.model.CoinDetail
import com.alexm.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCoinByIdUseCase @Inject constructor(
    private val repository: CoinRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId = coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: Exception) {
            emit(Resource.Error<CoinDetail>(
                message = ErrorHandler.handleExceptionMessage(exception = e))
            )
        }
    }.flowOn(dispatcher)
}