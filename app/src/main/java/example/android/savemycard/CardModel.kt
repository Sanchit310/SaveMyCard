package example.android.savemycard

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class CardModel(var cardNum : String) {

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}