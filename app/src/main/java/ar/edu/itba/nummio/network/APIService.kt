package ar.edu.itba.nummio.network

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
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

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
    @GET("user") //lo que va entre comillas es la ruta y se appendea al BASE_URL
    suspend fun getUserInfo(): UserInfo //NEEDS AUTH

    @POST("user")
    suspend fun createUser(@Body userRequest: UserRequest): UserInfo

    @POST("user/login")
    suspend fun login(@Body loginRequest: LoginRequest): TokenInfo

    @POST("user/verify")
    suspend fun verify(@Body tokenRequest: TokenInfo): UserInfo

    @POST("user/recover-password")
    suspend fun recoverPassword(@Body emailRequest: EmailRequest): SuccessAndMessage

    @POST("user/reset-password")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest) //no devuelve nada

    @POST("user/logout")
    suspend fun logout(): SuccessAndMessage
    //WALLET ENDPOINTS

}

object API {
    val retrofitService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}