package ar.edu.itba.nummio.data.network.api

import ar.edu.itba.nummio.data.network.model.NetworkCode
import ar.edu.itba.nummio.data.network.model.NetworkCredentials
import ar.edu.itba.nummio.data.network.model.NetworkEmailRequest
import ar.edu.itba.nummio.data.network.model.NetworkResetPasswordRequest
import ar.edu.itba.nummio.data.network.model.NetworkToken
import ar.edu.itba.nummio.data.network.model.NetworkUser
import ar.edu.itba.nummio.data.network.model.NetworkUserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @POST("user/login")
    suspend fun login(@Body credentials: NetworkCredentials): Response<NetworkToken>

    @POST("user")
    suspend fun register(@Body credentials: NetworkUserRequest): Response<NetworkUser>

    @POST("user/logout")
    suspend fun logout(): Response<Unit>

    @GET("user")
    suspend fun getCurrentUser(): Response<NetworkUser>

    @POST("user/verify")
    suspend fun verifyUser(@Body code: NetworkCode) : Response <NetworkUser>

    @POST("user/reset-password")
    suspend fun resetPassword(@Body resetPasswordRequest: NetworkResetPasswordRequest): Response<Unit>

    @POST("user/recover-password")
    suspend fun recoverPassword(@Body networkResetPasswordRequest: NetworkEmailRequest): Response<Unit>
}