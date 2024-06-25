package com.app.jetpack.mvvm.common.network


import retrofit2.Response
import java.io.IOException

@Suppress("LongParameterList")
data class ApiRetrofitException internal constructor(
    override val message: String?,
    /**
     * The request URL which produced the errors.
     */
    val url: String? = null,
    /**
     * Response object containing status code, headers, body, etc.
     */
    val response: Response<*>? = null,
    /**
     * The event kind which triggered this errors.
     */
    val kind: Kind,
    /**
     * The exception which was triggered
     */
    val exception: Throwable? = null,
    /**
     * The status code if kind is HTTP, else -1
     */
    val httpStatusCode: Int = -1,
    /**
     * Sonic specific reason code
     */
    val reasonCode: String? = null,
    /**
     * Error JSON from SONIC
     */
    val errorJson: String? = null
) : RuntimeException(message, exception) {

    /**
     * Identifies the event kind which triggered a [ApiRetrofitException].
     */
    enum class Kind {
        /**
         * An [IOException] occurred while communicating to the server.
         */
        NETWORK,

        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,

        /**
         * An internal errors occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }
}
