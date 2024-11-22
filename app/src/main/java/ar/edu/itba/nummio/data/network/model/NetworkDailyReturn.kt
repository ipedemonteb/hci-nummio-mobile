package ar.edu.itba.nummio.data.network.model

import ar.edu.itba.nummio.data.model.DailyReturn
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
data class NetworkDailyReturn (
    val id: Int,
    val returnGiven: Double,
    val balanceBefore: Double,
    val balanceAfter: Double,
    val createdAt: String?,
    val updatedAt: String?
) {
    fun asModel(): DailyReturn {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))
        return DailyReturn(
            id,
            returnGiven,
            balanceBefore,
            balanceAfter,
            createdAt = createdAt?.let { dateFormat.parse(createdAt) },
            updatedAt = updatedAt?.let { dateFormat.parse(updatedAt) }
        )
    }
}