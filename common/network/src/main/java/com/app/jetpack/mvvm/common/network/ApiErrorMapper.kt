package com.app.jetpack.mvvm.common.network


import java.io.IOException

internal const val HTTP_BAD_REQUEST = 400
internal const val HTTP_CODE_CLIENT_ERROR_MAX = 499

private const val HTTP_UNAUTHENTICATED = 401
private const val HTTP_CODE_SERVER_ERROR_MIN = 500
private const val HTTP_CODE_SERVER_ERROR_MAX = 599

/**
 * Maps Exceptions from the Api Client library into Http errors
 * And also maps general and IO exceptions
 */
internal class ApiErrorMapper {
    fun map(throwable: Throwable): ApiError {
        return when (throwable) {
            is ApiError -> throwable
            is ApiRetrofitException -> mapRetrofitException(throwable)
            is IOException -> ApiNetworkError(throwable.message, throwable)
            else -> ApiUnexpectedError(throwable.message, throwable)
        }
    }

    @Suppress("LongMethod")
    private fun mapRetrofitException(throwable: ApiRetrofitException): ApiError {
        return when (throwable.kind) {
            ApiRetrofitException.Kind.HTTP -> {
                when (throwable.httpStatusCode) {
                    HTTP_UNAUTHENTICATED -> {
                        ApiHttpError.ApiUnauthenticatedError(
                            message = throwable.message,
                            throwable = throwable,
                            url = throwable.url,
                            reasonCode = throwable.reasonCode,
                            errorJson = throwable.errorJson
                        )
                    }
                    // Ensures 401 doesn't match
                    HTTP_BAD_REQUEST, in (HTTP_UNAUTHENTICATED + 1..HTTP_CODE_CLIENT_ERROR_MAX) -> {
                        ApiHttpError.ApiClientError(
                            message = throwable.message,
                            throwable = throwable,
                            url = throwable.url,
                            httpStatusCode = throwable.httpStatusCode,
                            reasonCode = throwable.reasonCode,
                            errorJson = throwable.errorJson
                        )
                    }

                    in HTTP_CODE_SERVER_ERROR_MIN..HTTP_CODE_SERVER_ERROR_MAX -> {
                        ApiHttpError.ApiServerError(
                            message = throwable.message,
                            throwable = throwable,
                            url = throwable.url,
                            httpStatusCode = throwable.httpStatusCode,
                            reasonCode = throwable.reasonCode,
                            errorJson = throwable.errorJson
                        )
                    }

                    else -> {
                        ApiHttpError.ApiUnexpectedHttpError(
                            message = throwable.message,
                            throwable = throwable,
                            url = throwable.url,
                            httpStatusCode = throwable.httpStatusCode,
                            reasonCode = throwable.reasonCode,
                            errorJson = throwable.errorJson
                        )
                    }
                }
            }

            ApiRetrofitException.Kind.NETWORK -> {
                ApiNetworkError(
                    message = throwable.message,
                    throwable = throwable.exception as? IOException
                )
            }

            ApiRetrofitException.Kind.UNEXPECTED -> {
                ApiUnexpectedError(
                    message = throwable.message,
                    throwable = throwable.exception
                )
            }
        }
    }
}
