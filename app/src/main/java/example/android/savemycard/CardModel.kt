package example.android.savemycard

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class CardModel(val cardNum : String, val expDate: String, val holderName : String,
                     val cardName : String, val cardType : String, val bankName : String,
                     val bankIfsc : String, val cardCvv : String) {

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}