package ar.edu.itba.nummio.data.network.api

import ar.edu.itba.nummio.data.model.Balance
import ar.edu.itba.nummio.data.model.NewBalance
import ar.edu.itba.nummio.data.network.model.NetworkAliasRequest
import ar.edu.itba.nummio.data.network.model.NetworkAmount
import ar.edu.itba.nummio.data.network.model.NetworkBalance
import ar.edu.itba.nummio.data.network.model.NetworkCard
import ar.edu.itba.nummio.data.network.model.NetworkDailyInterest
import ar.edu.itba.nummio.data.network.model.NetworkDailyReturn
import ar.edu.itba.nummio.data.network.model.NetworkInvestment
import ar.edu.itba.nummio.data.network.model.NetworkNewBalance
import ar.edu.itba.nummio.data.network.model.NetworkWalletDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface WalletApiService {
    @GET("wallet/balance")
    suspend fun getBalance(): Response<NetworkBalance>

    @POST("wallet/recharge")
    suspend fun recharge(@Body rechargeRequest: NetworkAmount): Response<NetworkNewBalance>

    @GET("wallet/investment")
    suspend fun getInvestment(): Response<NetworkInvestment>

    @POST("wallet/invest")
    suspend fun invest(@Body investRequest: NetworkAmount): Response<NetworkWalletDetails>

    @POST("wallet/divest")
    suspend fun divest(@Body investRequest: NetworkAmount): Response<NetworkWalletDetails>

    @GET("wallet/cards")
    suspend fun getCards(): Response<List<NetworkCard>>

    @POST("wallet/cards")
    suspend fun addCard(@Body card: NetworkCard): Response<NetworkCard>

    @DELETE("wallet/cards/{cardId}")
    suspend fun deleteCard(@Path("cardId") cardId: Int): Response<Unit>

    @GET("wallet/daily-returns")
    suspend fun getDailyReturns(@Query("page") page: Int): Response<NetworkDailyReturn>

    @GET("wallet/daily-interest")
    suspend fun getDailyInterest(): Response<NetworkDailyInterest>

    @PUT("wallet/update-alias")
    suspend fun updateAlias(@Body aliasRequest: NetworkAliasRequest): Response<NetworkWalletDetails>

    @GET("wallet/details")
    suspend fun getDetails(): Response<NetworkWalletDetails>
}