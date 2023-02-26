package com.weatherapp.core_base.result

suspend fun <T, E> WorkResult<T>.map(mapper: suspend (data: T) -> E): WorkResult<E> {
    return when (this) {
        is WorkResult.Success -> WorkResult.Success(data = mapper(this.data))
        is WorkResult.Error -> this
        is WorkResult.Fail -> this
        is WorkResult.Empty -> this
    }
}

fun <T> WorkResult<T>.doIfSuccess(onSuccess: (T) -> Unit) {
    when (this) {
        is WorkResult.Success -> onSuccess(data)
        else -> {}
    }
}

fun <T : Any> WorkResult<T>.handle(
    onSuccess: (T) -> Unit,
    onNotSuccess: (WorkResult<T>) -> Unit
) {
    when (this) {
        is WorkResult.Success -> onSuccess(this.data)
        else -> onNotSuccess(this)
    }
}