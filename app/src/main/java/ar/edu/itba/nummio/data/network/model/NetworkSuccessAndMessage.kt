package ar.edu.itba.nummio.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkSuccessAndMessage(
    val success: Boolean,
    val message: String = ""
){
    fun asModel() = ar.edu.itba.nummio.data.model.SuccessAndMessage(
        success = success,
        message = message
    )
}