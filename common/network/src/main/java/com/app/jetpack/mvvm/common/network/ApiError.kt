package com.app.jetpack.mvvm.common.network

import java.io.IOException

private const val HTTP_UNAUTHENTICATED = 401

/**
 * Api Error which can decorate an error originating from the  be used as Base class for derived errors
 */
open class ApiError(override val message: String?, open val throwable: Throwable?) :
    Throwable(message, throwable)

/**
 * Unknown/Unexpected error used for General Use
 */
data class ApiUnexpectedError(
    override val message: String?,
    override val throwable: Throwable?
) : ApiError(message, throwable)

/**
 * Network or File access IO Exception
 */
data class ApiNetworkError(
    override val message: String?,
    override val throwable: IOException?
) : ApiError(message, throwable)

/**
 * API network request errors. These are mapped from the Client Retrofit library
 * These errors have been created from returned http status codes from network calls
 * Classification of these Http errors done with base class and more granular detail is provided in the derived classes
 */
@Suppress("LongParameterList")
sealed class ApiHttpError(
    override val message: String?,
    override val throwable: Throwable?,
    open val httpStatusCode: Int,
    open val url: String?,
    open val reasonCode: String?,
    open val errorJson: String?
) : ApiError(message, throwable) {

    /** 401 Unauthenticated */
    data class ApiUnauthenticatedError(
        override val message: String?,
        override val throwable: Throwable,
        override val httpStatusCode: Int = HTTP_UNAUTHENTICATED,
        override val url: String?,
        override val reasonCode: String? = null,
        override val errorJson: String? = null
    ) : ApiHttpError(message, throwable, httpStatusCode, url, reasonCode, errorJson)

    /** 4XX errors excluding Unauthenticated */
    data class ApiClientError(
        override val message: String?,
        override val throwable: Throwable,
        override val httpStatusCode: Int,
        override val url: String?,
        override val reasonCode: String? = null,
        override val errorJson: String? = null
    ) : ApiHttpError(message, throwable, httpStatusCode, url, reasonCode, errorJson)

    /** 5XX errors excluding Unauthenticated */
    data class ApiServerError(
        override val message: String?,
        override val throwable: Throwable,
        override val httpStatusCode: Int,
        override val url: String?,
        override val reasonCode: String? = null,
        override val errorJson: String? = null
    ) : ApiHttpError(message, throwable, httpStatusCode, url, reasonCode, errorJson)

    /** Other non 2xx http error */
    data class ApiUnexpectedHttpError(
        override val message: String?,
        override val throwable: Throwable,
        override val httpStatusCode: Int,
        override val url: String?,
        override val reasonCode: String? = null,
        override val errorJson: String? = null
    ) : ApiHttpError(message, throwable, httpStatusCode, url, reasonCode, errorJson)
}
