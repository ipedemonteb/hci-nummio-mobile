package ar.edu.itba.nummio.ui.home

import ar.edu.itba.nummio.data.model.Card
import ar.edu.itba.nummio.data.model.Error
import ar.edu.itba.nummio.data.model.PaymentData
import ar.edu.itba.nummio.data.model.User

data class HomeUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val hasBeenVerified: Boolean = false,
    val invalidCodeVerificationError: Boolean = false,
    val currentUser: User? = null,
    val cards: List<Card>? = null,
    val currentCard: Card? = null,
    val error: Error? = null,
    val isLandscape: Boolean = false,
    val currentBalance: Double? = null,
    val shouldUpdateBalance: Boolean = true,
    val cardToDelete: Int? = null,
    val paymentHistory : List<PaymentData>? = null,
    val shouldUpdatePaymentHistory: Boolean =  true,
    val latestGeneratedLink : String = "",
    val currentPayment: PaymentData? = null,
)

val HomeUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val HomeUiState.canGetAllCards: Boolean get() = isAuthenticated
val HomeUiState.canAddCard: Boolean get() = isAuthenticated
val HomeUiState.canDeleteCard: Boolean get() = isAuthenticated && currentCard != null
val HomeUiState.isLandscape: Boolean get() = isLandscape
val HomeUiState.shouldUpdateBalance: Boolean get() = shouldUpdateBalance
