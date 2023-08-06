package com.sevenpeakssoftware.core.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn

abstract class FlowCoroutineUseCase<I, O> {

    fun execute(param: I): Flow<O> = provide(param)
        .distinctUntilChanged()
        .flowOn(Dispatchers.IO)

    protected abstract fun provide(
        param: I
    ): Flow<O>
}
