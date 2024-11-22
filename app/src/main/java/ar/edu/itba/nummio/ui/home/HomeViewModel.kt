package ar.edu.itba.nummio.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ar.edu.itba.nummio.MyApplication
import ar.edu.itba.nummio.data.DataSourceException
import ar.edu.itba.nummio.data.model.Card
import ar.edu.itba.nummio.data.model.Error
import ar.edu.itba.nummio.data.repository.UserRepository
import ar.edu.itba.nummio.data.repository.WalletRepository
import ar.edu.itba.nummio.SessionManager
import ar.edu.itba.nummio.data.model.AliasRequest
import ar.edu.itba.nummio.data.model.Amount
import ar.edu.itba.nummio.data.repository.PaymentRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val walletRepository: WalletRepository,
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    fun login(username: String, password: String) = runOnViewModelScope(
        { userRepository.login(username, password) },
        { state, _ -> state.copy(isAuthenticated = true) }
    )

    fun logout() = runOnViewModelScope(
        { userRepository.logout() },
        { state, _ ->
            state.copy(
                isAuthenticated = false,
                currentUser = null,
                currentCard = null,
                cards = null
            )
        }
    )

    fun getCurrentUser() = runOnViewModelScope(
        { userRepository.getCurrentUser(uiState.currentUser == null) },
        { state, response -> state.copy(currentUser = response) }
    )
    fun verifyUser(code:String) = runOnViewModelScope(
        { userRepository.verifyUser(code) },
        { state, _ -> state.copy(hasBeenVerified = true) } //@TODO error checking?
    )
    fun register ( firstName: String,
                   lastName: String,
                   birthDate: String,
                   email: String,
                   password: String
    ) = runOnViewModelScope(
        { userRepository.register(firstName, lastName, birthDate, email, password) },
        { state, _ -> state.copy(hasBeenVerified = true) } //@TODO what state to set?
    )
    fun resetPassword(token: String, password: String) = runOnViewModelScope(
        { userRepository.resetPassword(token, password) },
        { state, _ -> state.copy(hasBeenVerified = true) } //@TODO what state to set?
    )
    fun recoverPassword(email: String) = runOnViewModelScope(
        { userRepository.recoverPassword(email) },
        { state, _ -> state.copy(hasBeenVerified = true) } //@TODO what state to set?
    )





    //WALLET

    fun getBalance() = runOnViewModelScope(
        { walletRepository.getBalance() },
        { state, _ -> state }
    )

    fun recharge(rechargeRequest: Amount) = runOnViewModelScope(
        { walletRepository.recharge(rechargeRequest) },
        { state, _ -> state }
    )

    fun getInvestment() = runOnViewModelScope(
        { walletRepository.getInvestment() },
        { state, _ -> state }
    )

    fun invest(investRequest: Amount) = runOnViewModelScope(
        { walletRepository.invest(investRequest) },
        { state, _ -> state }
    )

    fun divest(investRequest: Amount) = runOnViewModelScope(
        { walletRepository.divest(investRequest) },
        { state, _ -> state }
    )

    fun getCards() = runOnViewModelScope(
        { walletRepository.getCards(true) },
        { state, response -> state.copy(cards = response) }
    )

    fun addCard(card: Card) = runOnViewModelScope(
        {
            walletRepository.addCard(card)
        },
        { state, response ->
            state.copy(
                currentCard = response,
                cards = null
            )
        }
    )

    fun deleteCard(cardId: Int) = runOnViewModelScope(
        { walletRepository.deleteCard(cardId) },
        { state, _ ->
            state.copy(
                currentCard = null,
                cards = null
            )
        }
    )

    fun updateOrientation(isLandscape: Boolean) {
        uiState = uiState.copy(isLandscape = isLandscape)
    }

    // @TODO: cambiar state a todas las funciones de aca abajo y las de arriba que hacen lo mismo con el state
    fun getDailyReturns(page: Int) = runOnViewModelScope(
        { walletRepository.getDailyReturns(page) },
        { state, _ -> state }
    )

    fun getDailyInterest() = runOnViewModelScope(
        { walletRepository.getDailyInterest() },
        { state, _ -> state }
    )

    fun updateAlias(aliasRequest: AliasRequest) = runOnViewModelScope(
        { walletRepository.updateAlias(aliasRequest) },
        { state, _ -> state }
    )

    fun getDetails() = runOnViewModelScope(
        { walletRepository.getDetails() },
        { state, _ -> state }
    )

    //PAYMENT

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (HomeUiState, R) -> HomeUiState
    ): Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            uiState = updateState(uiState, response).copy(isFetching = false)
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
            Log.e(TAG, "Coroutine execution failed", e)
        }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "")
        } else {
            Error(null, e.message ?: "")
        }
    }

    companion object {
        const val TAG = "UI Layer"

        fun provideFactory(
            application: MyApplication
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(
                    application.sessionManager,
                    application.userRepository,
                    application.walletRepository,
                    application.paymentRepository) as T
            }
        }
    }
}