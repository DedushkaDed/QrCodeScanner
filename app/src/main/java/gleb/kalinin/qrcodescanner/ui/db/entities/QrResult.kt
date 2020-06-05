package gleb.kalinin.qrcodescanner.ui.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// Это таблица(Базы-данных) - в которой мы храним 4 значения.
// 0 - PrimaryKey (id)
// 1 - Result (Qr-code)
// 2 - Result type (Text, Cite and etc..)
// 3 - Favorite or not
// 4 - Time

@Entity
data class QrResult (

    @PrimaryKey (autoGenerate = true)
    val id: Int ? = null,

    @ColumnInfo(name = "result")
    val result : String?,

    @ColumnInfo(name = "result_type")
    val resultType: String?,

    @ColumnInfo(name = "favorite")
    val favorite : Boolean = false,

    @ColumnInfo(name = "time")
    val calendar: Calendar
)
