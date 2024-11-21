package ar.edu.itba.nummio.data.network

import ar.edu.itba.nummio.data.network.api.UserApiService
import ar.edu.itba.nummio.data.network.model.NetworkCredentials
import ar.edu.itba.nummio.data.network.model.NetworkUser
import ar.edu.itba.nummio.SessionManager
import ar.edu.itba.nummio.data.network.model.NetworkCode
import ar.edu.itba.nummio.data.network.model.NetworkEmailRequest
import ar.edu.itba.nummio.data.network.model.NetworkResetPasswordRequest
import ar.edu.itba.nummio.data.network.model.NetworkUserRequest

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

    suspend fun verifyUser(code: String): NetworkUser {
        return handleApiResponse { userApiService.verifyUser(NetworkCode(code)) }
    }

    suspend fun register(
        firstName: String,
        lastName: String,
        birthDate: String,
        email: String,
        password: String
    ): NetworkUser {
        return handleApiResponse { userApiService.register(NetworkUserRequest(firstName,lastName,birthDate,email, password)) }
    }

    suspend fun resetPassword(token: String, password: String) {
        handleApiResponse { userApiService.resetPassword(NetworkResetPasswordRequest(token, password)) }
    }
    suspend fun recoverPassword(email: String) {
        handleApiResponse { userApiService.recoverPassword(NetworkEmailRequest(email)) }
    }
}