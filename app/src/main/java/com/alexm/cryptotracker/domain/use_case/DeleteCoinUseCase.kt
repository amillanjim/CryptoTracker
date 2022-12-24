package com.alexm.cryptotracker.domain.use_case

import com.alexm.cryptotracker.common.ErrorHandler
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.di.app.DefaultDispatcher
import com.alexm.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeleteCoinUseCase  @Inject constructor(
    private val coinRepository: CoinRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
){
    operator fun invoke(coin: CoinEntity?): Flow<Resource<Boolean>> = flow {
        try {
            coinRepository.deleteCoin(coin = coin)
            emit(Resource.Success<Boolean>(true))
        } catch (e: Exception) {
            emit(
                Resource.Error<Boolean>(
                    message = ErrorHandler.handleExceptionMessage(exception = e))
            )
        }
    }.flowOn(dispatcher)
}