package com.alexm.cryptotracker.domain.use_case

import com.alexm.cryptotracker.di.app.DefaultDispatcher
import com.alexm.cryptotracker.common.ErrorHandler
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.data.mapper.toCoin
import com.alexm.cryptotracker.domain.model.Coin
import com.alexm.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
){
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: Exception) {
            emit(Resource.Error<List<Coin>>(
                message = ErrorHandler.handleExceptionMessage(exception = e))
            )
        }
    }.flowOn(dispatcher)
}