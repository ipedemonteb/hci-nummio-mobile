package ar.edu.itba.nummio.data.repository

import ar.edu.itba.nummio.data.model.PaymentData
import ar.edu.itba.nummio.data.model.User
import ar.edu.itba.nummio.data.network.PaymentRemoteDataSource
import kotlinx.coroutines.sync.Mutex

class PaymentRepository(
    private val remoteDataSource: PaymentRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val currentUserMutex = Mutex()

    // Cache of the current user got from the network.
    private var currentUser: User? = null


    suspend fun getPayments(
        page: Int,
        direction: String,
        pending: String?,
        type: String?,
        range: String?,
        source: String?,
        cardId: Int?
    ): List<PaymentData> {
        return remoteDataSource.getPayments(page, direction, pending, type, range, source, cardId)
            .map { it.asModel() }
    }
    suspend fun getPayment(id: Int): PaymentData {
        return remoteDataSource.getPayment(id).asModel()
    }
    suspend fun getPaymentByLink(linkUuid: String): PaymentData {
        return remoteDataSource.getPaymentByLink(linkUuid).asModel()
    }
    suspend fun payByLink(linkUuid: String, type: String) {
        remoteDataSource.payByLink(linkUuid, type)
    }
}