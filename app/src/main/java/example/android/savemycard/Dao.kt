package example.android.savemycard

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {

    @Insert
    suspend fun addCard(cardModel: CardModel)

    @Update
    suspend fun updateCard(cardModel: CardModel)

    @Query("SELECT * FROM card_table")
    fun getAllCards() : LiveData<List<CardModel>>

}