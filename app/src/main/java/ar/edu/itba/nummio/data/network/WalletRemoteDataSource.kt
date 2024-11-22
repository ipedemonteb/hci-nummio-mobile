package ar.edu.itba.nummio.data.network

import ar.edu.itba.nummio.data.network.api.WalletApiService
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
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

class WalletRemoteDataSource(
    private val walletApiService: WalletApiService
) : RemoteDataSource() {


    suspend fun getBalance(): NetworkBalance {
        return handleApiResponse {
            walletApiService.getBalance()
        }
    }


    suspend fun recharge(@Body rechargeRequest: NetworkAmount): NetworkNewBalance {
        return handleApiResponse {
            walletApiService.recharge(rechargeRequest)
        }
    }

    suspend fun getInvestment(): NetworkInvestment {
        return handleApiResponse {
            walletApiService.getInvestment()
        }
    }

    suspend fun invest(@Body investRequest: NetworkAmount): NetworkWalletDetails {
        return handleApiResponse {
            walletApiService.invest(investRequest)
        }
    }

    suspend fun divest(@Body investRequest: NetworkAmount): NetworkWalletDetails {
        return handleApiResponse {
            walletApiService.divest(investRequest)
        }
    }

    suspend fun getCards(): List<NetworkCard> {
        return handleApiResponse {
            walletApiService.getCards()
        }
    }

    suspend fun addCard(card: NetworkCard): NetworkCard {
        return handleApiResponse {
            walletApiService.addCard(card)
        }
    }

    suspend fun deleteCard(cardId: Int) {
        handleApiResponse {
            walletApiService.deleteCard(cardId)
        }
    }

    suspend fun getDailyReturns(@Query("page") page: Int): NetworkDailyReturn {
        return handleApiResponse {
            walletApiService.getDailyReturns(page)
        }
    }

    suspend fun getDailyInterest(): NetworkDailyInterest {
        return handleApiResponse {
            walletApiService.getDailyInterest()
        }
    }

    suspend fun updateAlias(@Body aliasRequest: NetworkAliasRequest): NetworkWalletDetails {
        return handleApiResponse {
            walletApiService.updateAlias(aliasRequest)
        }
    }

    suspend fun getDetails(): NetworkWalletDetails {
        return handleApiResponse {
            walletApiService.getDetails()
        }
    }
}