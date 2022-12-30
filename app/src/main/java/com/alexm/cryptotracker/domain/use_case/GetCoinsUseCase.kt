package com.alexm.cryptotracker.domain.use_case

import com.alexm.cryptotracker.base.BaseErrorHandler
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.common.flowExceptionHandler
import com.alexm.cryptotracker.data.mapper.toCoin
import com.alexm.cryptotracker.di.app.IoDispatcher
import com.alexm.cryptotracker.domain.model.Coin
import com.alexm.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
){
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())
        val coins = repository.getCoins().map { it.toCoin() }
        emit(Resource.Success(coins))
    }.catch {e ->
        emit(Resource.Error(
            message = BaseErrorHandler.handleExceptionMessage(exception = e as Exception))
        )
    }.flowOn(dispatcher + flowExceptionHandler)
}