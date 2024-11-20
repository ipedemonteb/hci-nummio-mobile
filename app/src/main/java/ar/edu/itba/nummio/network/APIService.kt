package ar.edu.itba.nummio.network

import PaymentRequest
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

import ar.edu.itba.nummio.model.*
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
    suspend fun getUserInfo(): UserInfo

    @POST("api/user")
    suspend fun createUser(@Body userRequest: UserRequest): UserInfo

    @POST("api/user/login")
    suspend fun login(@Body loginRequest: LoginRequest): TokenInfo

    @POST("api/user/verify")
    suspend fun verify(@Body tokenRequest: TokenInfo): UserInfo

    @POST("api/user/recover-password")
    suspend fun recoverPassword(@Body emailRequest: EmailRequest): SuccessAndMessage

    @POST("api/user/reset-password")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest) //no devuelve nada

    //NEEDS AUTH
    @POST("api/user/logout")
    suspend fun logout(): SuccessAndMessage

    //WALLET ENDPOINTS
    //(ALL NEED AUTH)
    @GET("api/wallet/balance")
    suspend fun getBalance(): Balance

    @POST("api/wallet/recharge")
    suspend fun recharge(@Body rechargeRequest: RechargeRequest): NewBalance

    @GET("api/wallet/investment")
    suspend fun getInvestment(): Investment

    @POST("api/wallet/invest")
    suspend fun invest(@Body investRequest:RechargeRequest): NewBalance

    @POST("api/wallet/divest")
    suspend fun divest(@Body investRequest:RechargeRequest): NewBalance

    @GET("api/wallet/cards")
    suspend fun getCards(): List<CardData>

    @POST("api/wallet/cards")
    suspend fun addCard(@Body cardRequest: CardRequest): CardData

    @DELETE("api/wallet/cards{id}")
    suspend fun deleteCard(@Path("id") id: Int): StatusData

    @GET("api/wallet/daily-returns")
    suspend fun getDailyReturns(@Query("page") page: Int): List<DailyReturn>

    @GET("api/wallet/daily-interest")
    suspend fun getDailyInterest(): DailyInterest

    @PUT("api/wallet/update-alias")
    suspend fun updateAlias(@Body aliasRequest: AliasRequest): WalletDetails

    @GET("api/wallet/details")
    suspend fun getDetails(): WalletDetails

    //PAYMENT ENDPOINTS
    //(ALL NEED AUTH)

    @POST("api/payment")
    suspend fun makePayment(@Body paymentRequest: PaymentRequest): NewBalance

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
    ): List<PaymentData>

    @GET("api/payment/{id}")
    suspend fun getPayment(@Path("id") id: Int): PaymentData

    @GET("api/payment/link/{linkUuid}")
    suspend fun getPaymentByLink(@Path("linkUuid") linkUuid: String): PaymentData

    @POST("api/payment/link/{linkUuid}")
    suspend fun payByLink(
        @Path("linkUuid") linkUuid: String,
        @Body type: PaymentType
    ): SuccessAndMessage

}

object API {
    val retrofitService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}