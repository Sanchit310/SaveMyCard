package example.android.savemycard

import androidx.lifecycle.LiveData

class AppRepository(private val dao : Dao) {

    val allCards : LiveData<List<CardModel>> = dao.getAllCards()

    suspend fun insertCard(cardModel: CardModel){
        dao.addCard(cardModel)
    }

}