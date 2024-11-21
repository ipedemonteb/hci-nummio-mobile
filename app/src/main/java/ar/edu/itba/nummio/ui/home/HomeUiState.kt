package ar.edu.itba.nummio.ui.home

import ar.edu.itba.nummio.data.model.Card
import ar.edu.itba.nummio.data.model.Error
import ar.edu.itba.nummio.data.model.User

data class HomeUiState(
    val isAuthenticated: Boolean = true,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val cards: List<Card>? = null,
    val currentCard: Card? = null,
    val error: Error? = null
)

val HomeUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val HomeUiState.canGetAllCards: Boolean get() = isAuthenticated
val HomeUiState.canAddCard: Boolean get() = isAuthenticated
val HomeUiState.canDeleteCard: Boolean get() = isAuthenticated && currentCard != null
