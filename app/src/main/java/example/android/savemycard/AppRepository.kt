package example.android.savemycard

import androidx.lifecycle.LiveData

class AppRepository(private val dao : Dao) {

    val allCards : LiveData<List<CardModel>> = dao.getAllCards()

    suspend fun insertCard(cardModel: CardModel){
        dao.addCard(cardModel)
    }

    suspend fun updateCard(cardModel: CardModel){
        dao.updateCard(cardModel)
    }

    suspend fun deleteCard(cardModel: CardModel){
        dao.deleteCard(cardModel)
    }

}