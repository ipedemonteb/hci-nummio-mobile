package ar.edu.itba.nummio.data.model

class SuccessAndMessage(
    var success: String,
    var message: String = ""
){
    fun asNetworkModel() = ar.edu.itba.nummio.data.network.model.NetworkSuccessAndMessage(
        success = success,
        message = message
    )
}