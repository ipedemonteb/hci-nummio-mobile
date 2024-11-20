package ar.edu.itba.nummio.data.network

import ar.edu.itba.nummio.data.network.api.UserApiService
import ar.edu.itba.nummio.data.network.model.NetworkCredentials
import ar.edu.itba.nummio.data.network.model.NetworkUser
import ar.edu.itba.nummio.SessionManager

class UserRemoteDataSource(
    private val sessionManager: SessionManager,
    private val userApiService: UserApiService
) : RemoteDataSource() {

    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            userApiService.login(NetworkCredentials(username, password))
        }
        sessionManager.saveAuthToken(response.token)
    }

    suspend fun logout() {
        handleApiResponse { userApiService.logout() }
        sessionManager.removeAuthToken()
    }

    suspend fun getCurrentUser(): NetworkUser {
        return handleApiResponse { userApiService.getCurrentUser() }
    }
}