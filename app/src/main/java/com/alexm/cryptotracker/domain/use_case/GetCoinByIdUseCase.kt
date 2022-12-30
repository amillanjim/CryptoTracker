package com.alexm.cryptotracker.domain.use_case

import com.alexm.cryptotracker.base.BaseErrorHandler
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.common.flowExceptionHandler
import com.alexm.cryptotracker.data.mapper.toCoinDetail
import com.alexm.cryptotracker.di.app.IoDispatcher
import com.alexm.cryptotracker.domain.model.CoinDetail
import com.alexm.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCoinByIdUseCase @Inject constructor(
    private val repository: CoinRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId = coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: Exception) {
            emit(Resource.Error(
                message = BaseErrorHandler.handleExceptionMessage(exception = e))
            )
        }
    }.flowOn(dispatcher + flowExceptionHandler)
}