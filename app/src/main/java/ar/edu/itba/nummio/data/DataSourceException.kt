package ar.edu.itba.nummio.data

class DataSourceException(
    var code: Int,
    message: String,
) : Exception(message)