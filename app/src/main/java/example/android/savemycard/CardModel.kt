package example.android.savemycard

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "card_table")
data class CardModel(val cardNum : String, val expDate: String, val holderName : String,
                     val cardName : String, val cardType : String, val bankName : String,
                     val accountNum : String, val bankIfsc : String, val cardCvv : String) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}