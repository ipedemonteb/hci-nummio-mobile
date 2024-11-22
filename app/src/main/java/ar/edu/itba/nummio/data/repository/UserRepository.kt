package ar.edu.itba.nummio.data.repository

import ar.edu.itba.nummio.data.model.User
import ar.edu.itba.nummio.data.network.UserRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val currentUserMutex = Mutex()
    // Cache of the current user got from the network.
    private var currentUser: User? = null

    suspend fun login(username: String, password: String) {
        remoteDataSource.login(username, password)
    }

    suspend fun logout() {
        remoteDataSource.logout()
    }

    suspend fun getCurrentUser(refresh: Boolean) : User? {
        if (refresh || currentUser == null) {
            val result = remoteDataSource.getCurrentUser()
            // Thread-safe write to latestNews
            currentUserMutex.withLock {
                this.currentUser = result.asModel()
            }
        }

        return currentUserMutex.withLock { this.currentUser }
    }
    suspend fun verifyUser(code:String):User{
        return remoteDataSource.verifyUser(code).asModel()
    }

    suspend fun register( firstName: String,
                          lastName: String,
                          birthDate: String,
                          email: String,
                          password: String
    ):User{
        return remoteDataSource.register(firstName, lastName, birthDate, email, password).asModel()
    }

    suspend fun resetPassword(token: String, password: String) {
        remoteDataSource.resetPassword(token, password)
    }
    suspend fun recoverPassword(email: String) {
        remoteDataSource.recoverPassword(email)
    }
}