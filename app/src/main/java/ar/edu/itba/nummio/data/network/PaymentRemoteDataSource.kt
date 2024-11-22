package ar.edu.itba.nummio.data.network

import ar.edu.itba.nummio.data.model.NewBalance
import ar.edu.itba.nummio.data.network.api.PaymentApiService
import ar.edu.itba.nummio.data.network.model.NetworkCard
import ar.edu.itba.nummio.data.network.model.NetworkNewBalance
import ar.edu.itba.nummio.data.network.model.NetworkPaymentData
import ar.edu.itba.nummio.data.network.model.NetworkPaymentRequest
import ar.edu.itba.nummio.data.network.model.NetworkPaymentType
import ar.edu.itba.nummio.data.network.model.NetworkSuccessAndMessage

class PaymentRemoteDataSource(
    private val paymentApiService: PaymentApiService
) : RemoteDataSource() {

    suspend fun makePayment(networkPaymentRequest: NetworkPaymentRequest): NetworkNewBalance {
        return handleApiResponse {
            paymentApiService.makePayment(networkPaymentRequest)
        }
    }

    suspend fun getPayments(
        page: Int,
        direction: String,
        pending: String?,
        type: String?,
        range: String?,
        source: String?,
        cardId: Int?
    ): List<NetworkPaymentData> {
        return handleApiResponse {
            paymentApiService.getPayments(page, direction, pending, type, range, source, cardId)
        }
    }

    suspend fun getPayment(id: Int): NetworkPaymentData {
        return handleApiResponse {
            paymentApiService.getPayment(id)
        }
    }

    suspend fun getPaymentByLink(linkUuid: String): NetworkPaymentData {
        return handleApiResponse {
            paymentApiService.getPaymentByLink(linkUuid)
        }
    }

    suspend fun payByLink(linkUuid: String, type: String): NetworkSuccessAndMessage {
        return handleApiResponse {
            paymentApiService.payByLink(linkUuid, NetworkPaymentType(type))
        }
    }
}