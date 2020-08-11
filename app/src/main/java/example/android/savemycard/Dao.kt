package example.android.savemycard

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Insert
    suspend fun addCard(cardModel: CardModel)

    @Update
    suspend fun updateCard(cardModel: CardModel)

    @Delete
    suspend fun deleteCard(cardModel: CardModel)

    @Query("SELECT * FROM card_table")
    fun getAllCards() : LiveData<List<CardModel>>

}