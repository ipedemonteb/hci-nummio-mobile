package ar.edu.itba.nummio.data.network.api

import NetworkPaymentRequest
import ar.edu.itba.nummio.data.network.model.NetworkCredentials
import ar.edu.itba.nummio.data.network.model.NetworkNewBalance
import ar.edu.itba.nummio.data.network.model.NetworkPaymentData
import ar.edu.itba.nummio.data.network.model.NetworkPaymentType
import ar.edu.itba.nummio.data.network.model.NetworkSuccessAndMessage
import ar.edu.itba.nummio.data.network.model.NetworkToken
import ar.edu.itba.nummio.data.network.model.NetworkUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PaymentApiService {

    @POST("api/payment")
    suspend fun makePayment(@Body networkPaymentRequest: NetworkPaymentRequest): Response<NetworkNewBalance>

    //payment request has different data depending on type?
    //@TODO handle card errors
    @GET("api/payment")
    suspend fun getPayments(
        @Query("page") page: Int,
        @Query("direction") direction: String, //@TODO el ? va o no? es ASC o DESC, no null, puedo poner default quizas (lo dejo sin x ahora)
        @Query("pending") pending: String?,
        @Query("type") type: String?,
        @Query("range") range: String?,
        @Query("source") source: String?,
        @Query("cardId") cardId: Int?
    ): Response<List<NetworkPaymentData>>

    @GET("api/payment/{id}")
    suspend fun getPayment(@Path("id") id: Int): Response<NetworkPaymentData>

    @GET("api/payment/link/{linkUuid}")
    suspend fun getPaymentByLink(@Path("linkUuid") linkUuid: String): Response<NetworkPaymentData>

    @POST("api/payment/link/{linkUuid}")
    suspend fun payByLink(
        @Path("linkUuid") linkUuid: String,
        @Body type: NetworkPaymentType
    ): Response<NetworkSuccessAndMessage>
}