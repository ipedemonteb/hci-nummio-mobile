package ar.edu.itba.nummio.data.repository

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

//    suspend fun login(username: String, password: String) {
//        remoteDataSource.login(username, password)
//    }
//
//    suspend fun logout() {
//        remoteDataSource.logout()
//    }
//
//    suspend fun getCurrentUser(refresh: Boolean) : User? {
//        if (refresh || currentUser == null) {
//            val result = remoteDataSource.getCurrentUser()
//            // Thread-safe write to latestNews
//            currentUserMutex.withLock {
//                this.currentUser = result.asModel()
//            }
//        }
//
//        return currentUserMutex.withLock { this.currentUser }
//    }
}