package ar.edu.itba.nummio.data.repository

import ar.edu.itba.nummio.data.model.AliasRequest
import ar.edu.itba.nummio.data.model.Amount
import ar.edu.itba.nummio.data.model.Balance
import ar.edu.itba.nummio.data.model.Card
import ar.edu.itba.nummio.data.model.DailyInterest
import ar.edu.itba.nummio.data.model.DailyReturn
import ar.edu.itba.nummio.data.model.Investment
import ar.edu.itba.nummio.data.model.NewBalance
import ar.edu.itba.nummio.data.model.WalletDetails
import ar.edu.itba.nummio.data.network.WalletRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class WalletRepository(
    private val remoteDataSource: WalletRemoteDataSource
) {
    // Mutex to make writes to cached values thread-safe.
    private val cardsMutex = Mutex()
    // Cache of the latest sports got from the network.
    private var cards: List<Card> = emptyList()

    suspend fun getBalance(): Balance {
        return remoteDataSource.getBalance().asModel()
    }

    suspend fun recharge(rechargeRequest: Amount): NewBalance {
        return remoteDataSource.recharge(rechargeRequest.asNetworkModel()).asModel()
    }

    suspend fun getInvestment(): Investment {
        return remoteDataSource.getInvestment().asModel()
    }

    suspend fun invest(investRequest: Amount): WalletDetails {
        return remoteDataSource.invest(investRequest.asNetworkModel()).asModel()
    }

    suspend fun divest(investRequest: Amount): WalletDetails {
        return remoteDataSource.divest(investRequest.asNetworkModel()).asModel()
    }

    suspend fun getCards(refresh: Boolean = false): List<Card> {
        if (refresh || cards.isEmpty()) {
            val result = remoteDataSource.getCards()
            // Thread-safe write to sports
            cardsMutex.withLock {
                this.cards = result.map { it.asModel() }
            }
        }

        return cardsMutex.withLock { this.cards }
    }

    suspend fun addCard(card: Card) : Card {
        val newCard = remoteDataSource.addCard(card.asNetworkModel()).asModel()
        cardsMutex.withLock {
            this.cards = emptyList()
        }
        return newCard
    }

    suspend fun deleteCard(cardId: Int) {
        remoteDataSource.deleteCard(cardId)
        cardsMutex.withLock {
            this.cards = emptyList()
        }
    }

    suspend fun getDailyReturns(page: Int): DailyReturn {
        return remoteDataSource.getDailyReturns(page).asModel()
    }

    suspend fun getDailyInterest(): DailyInterest {
        return remoteDataSource.getDailyInterest().asModel()
    }

    suspend fun updateAlias(aliasRequest: AliasRequest): WalletDetails {
        return remoteDataSource.updateAlias(aliasRequest.asNetworkModel()).asModel()
    }

    suspend fun getDetails(): WalletDetails {
        return remoteDataSource.getDetails().asModel()
    }
}