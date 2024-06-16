package com.app.jetpack.mvvm.common.domain

import kotlinx.coroutines.flow.Flow

interface IParams

sealed interface UseCase {
    interface Suspending<R> : UseCase {
        suspend operator fun invoke(): R
    }

    interface SuspendingParameterized<T, R> : UseCase {
        suspend operator fun invoke(param: T): R
    }

    interface SuspendingFlow<R> : UseCase {
        suspend operator fun invoke(): Flow<R>
    }

    interface SuspendingFlowParameterized<T, R> : UseCase {
        suspend operator fun invoke(param: T): Flow<R>
    }

    interface AsyncStream<R> : UseCase {
        operator fun invoke(): Flow<R>
    }

    interface AsyncStreamParameterized<T, R> : UseCase {
        operator fun invoke(param: T): Flow<R>
    }
}
