package ar.edu.itba.nummio.data

import PaymentRequest
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "localhost:8080/"

private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

class AuthInterceptor(private val tokenProvider: () -> String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider()
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}

// Provide a dynamic token:
private var currentToken: String = ""

// Update it dynamically:
fun updateToken(newToken: String) {
    currentToken = newToken
}


private val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
    .addInterceptor(AuthInterceptor { currentToken }).build()

private val json = Json {
    ignoreUnknownKeys = true
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL).client(okHttpClient).build()

interface APIService {
    //USER ENDPOINTS

    //NEEDS AUTH
    @GET("api/user") //lo que va entre comillas es la ruta y se appendea al BASE_URL
    suspend fun getUserInfo(): ar.edu.itba.nummio.data.network.model.UserInfo

    @POST("api/user")
    suspend fun createUser(@Body userRequest: ar.edu.itba.nummio.data.network.model.UserRequest): ar.edu.itba.nummio.data.network.model.UserInfo

    @POST("api/user/login")
    suspend fun login(@Body loginRequest: ar.edu.itba.nummio.data.network.model.LoginRequest): ar.edu.itba.nummio.data.network.model.TokenInfo

    @POST("api/user/verify")
    suspend fun verify(@Body tokenRequest: ar.edu.itba.nummio.data.network.model.TokenInfo): ar.edu.itba.nummio.data.network.model.UserInfo

    @POST("api/user/recover-password")
    suspend fun recoverPassword(@Body emailRequest: ar.edu.itba.nummio.data.network.model.EmailRequest): ar.edu.itba.nummio.data.network.model.SuccessAndMessage

    @POST("api/user/reset-password")
    suspend fun resetPassword(@Body resetPasswordRequest: ar.edu.itba.nummio.data.network.model.ResetPasswordRequest) //no devuelve nada

    //NEEDS AUTH
    @POST("api/user/logout")
    suspend fun logout(): ar.edu.itba.nummio.data.network.model.SuccessAndMessage

    //WALLET ENDPOINTS
    //(ALL NEED AUTH)
    @GET("api/wallet/balance")
    suspend fun getBalance(): ar.edu.itba.nummio.data.network.model.Balance

    @POST("api/wallet/recharge")
    suspend fun recharge(@Body rechargeRequest: ar.edu.itba.nummio.data.network.model.RechargeRequest): ar.edu.itba.nummio.data.network.model.NewBalance

    @GET("api/wallet/investment")
    suspend fun getInvestment(): ar.edu.itba.nummio.data.network.model.Investment

    @POST("api/wallet/invest")
    suspend fun invest(@Body investRequest: ar.edu.itba.nummio.data.network.model.RechargeRequest): ar.edu.itba.nummio.data.network.model.NewBalance

    @POST("api/wallet/divest")
    suspend fun divest(@Body investRequest: ar.edu.itba.nummio.data.network.model.RechargeRequest): ar.edu.itba.nummio.data.network.model.NewBalance

    @GET("api/wallet/cards")
    suspend fun getCards(): List<ar.edu.itba.nummio.data.network.model.CardData>

    @POST("api/wallet/cards")
    suspend fun addCard(@Body cardRequest: ar.edu.itba.nummio.data.network.model.CardRequest): ar.edu.itba.nummio.data.network.model.CardData

    @DELETE("api/wallet/cards{id}")
    suspend fun deleteCard(@Path("id") id: Int): ar.edu.itba.nummio.data.network.model.StatusData

    @GET("api/wallet/daily-returns")
    suspend fun getDailyReturns(@Query("page") page: Int): List<ar.edu.itba.nummio.data.network.model.DailyReturn>

    @GET("api/wallet/daily-interest")
    suspend fun getDailyInterest(): ar.edu.itba.nummio.data.network.model.DailyInterest

    @PUT("api/wallet/update-alias")
    suspend fun updateAlias(@Body aliasRequest: ar.edu.itba.nummio.data.network.model.AliasRequest): ar.edu.itba.nummio.data.network.model.WalletDetails

    @GET("api/wallet/details")
    suspend fun getDetails(): ar.edu.itba.nummio.data.network.model.WalletDetails

    //PAYMENT ENDPOINTS
    //(ALL NEED AUTH)

    @POST("api/payment")
    suspend fun makePayment(@Body paymentRequest: PaymentRequest): ar.edu.itba.nummio.data.network.model.NewBalance

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
    ): List<ar.edu.itba.nummio.data.network.model.PaymentData>

    @GET("api/payment/{id}")
    suspend fun getPayment(@Path("id") id: Int): ar.edu.itba.nummio.data.network.model.PaymentData

    @GET("api/payment/link/{linkUuid}")
    suspend fun getPaymentByLink(@Path("linkUuid") linkUuid: String): ar.edu.itba.nummio.data.network.model.PaymentData

    @POST("api/payment/link/{linkUuid}")
    suspend fun payByLink(
        @Path("linkUuid") linkUuid: String,
        @Body type: ar.edu.itba.nummio.data.network.model.PaymentType
    ): ar.edu.itba.nummio.data.network.model.SuccessAndMessage

}

object API {
    val retrofitService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}