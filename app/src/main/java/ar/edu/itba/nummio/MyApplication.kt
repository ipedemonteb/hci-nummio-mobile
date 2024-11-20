package ar.edu.itba.nummio

import android.app.Application
import ar.edu.itba.nummio.data.network.PaymentRemoteDataSource
import ar.edu.itba.nummio.data.network.UserRemoteDataSource
import ar.edu.itba.nummio.data.network.WalletRemoteDataSource
import ar.edu.itba.nummio.data.network.api.RetrofitClient
import ar.edu.itba.nummio.data.repository.PaymentRepository
import ar.edu.itba.nummio.data.repository.UserRepository
import ar.edu.itba.nummio.data.repository.WalletRepository

class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getUserApiService(this))

    private val walletRemoteDataSource: WalletRemoteDataSource
        get() = WalletRemoteDataSource(RetrofitClient.getWalletApiService(this))
    private val paymentRemoteDataSource: PaymentRemoteDataSource
        get() = PaymentRemoteDataSource(RetrofitClient.getPaymentApiService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val walletRepository: WalletRepository
        get() = WalletRepository(walletRemoteDataSource)
    val paymentRepository: PaymentRepository
        get() = PaymentRepository(paymentRemoteDataSource)
}