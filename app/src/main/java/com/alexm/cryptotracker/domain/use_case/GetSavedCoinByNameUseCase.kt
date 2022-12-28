package com.alexm.cryptotracker.domain.use_case

import com.alexm.cryptotracker.di.app.DefaultDispatcher
import com.alexm.cryptotracker.base.BaseErrorHandler
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSavedCoinByNameUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
){
    operator fun invoke(coinId: String): Flow<Resource<CoinEntity>> = flow {
        try {
            emit(Resource.Loading())
            val coin = coinRepository.observeCoinByName(coinId = coinId)
            emit(Resource.Success(coin))
        } catch (e: Exception) {
            emit(Resource.Error(
                message = BaseErrorHandler.handleExceptionMessage(exception = e))
            )
        }
    }.flowOn(dispatcher)
}