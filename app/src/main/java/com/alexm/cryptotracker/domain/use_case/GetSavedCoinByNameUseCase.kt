package com.alexm.cryptotracker.domain.use_case

import com.alexm.cryptotracker.base.BaseErrorHandler
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.common.flowExceptionHandler
import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.di.app.IoDispatcher
import com.alexm.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import java.sql.SQLException
import javax.inject.Inject

class GetSavedCoinByNameUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
){
    operator fun invoke(coinId: String): Flow<Resource<CoinEntity>> = flow {
        emit(Resource.Empty())
        val coin = coinRepository.observeCoinByName(coinId = coinId)
        emit(Resource.Success(coin))
    }.catch {e ->
        emit(Resource.Error(
            message = BaseErrorHandler.handleExceptionMessage(exception = e as Exception))
        )
    }.retryWhen{ cause, attempt -> cause is SQLException || attempt < 2
    }.flowOn(dispatcher + flowExceptionHandler)
}