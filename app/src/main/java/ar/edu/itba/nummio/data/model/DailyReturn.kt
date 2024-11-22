package ar.edu.itba.nummio.data.model

import ar.edu.itba.nummio.data.network.model.NetworkDailyReturn
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DailyReturn(
    val id: Int,
    val returnGiven: Double,
    val balanceBefore: Double,
    val balanceAfter: Double,
    val createdAt: Date? = null,
    val updatedAt: Date? = null
) {
    fun asNetworkModel(): NetworkDailyReturn {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))
        return NetworkDailyReturn(
            id,
            returnGiven,
            balanceBefore,
            balanceAfter,
            createdAt = createdAt?.let { dateFormat.format(createdAt) },
            updatedAt = updatedAt?.let { dateFormat.format(updatedAt) }
        )
    }
}