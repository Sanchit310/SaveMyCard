package example.android.savemycard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val appRepository: AppRepository
    val allCard : LiveData<List<CardModel>>

    init {
        val dao = AppRoomDatabase.getDatabase(application).dao()
        appRepository = AppRepository(dao)
        allCard  = appRepository.allCards

    }

    fun addCard(cardModel: CardModel){
        viewModelScope.launch {
            appRepository.insertCard(cardModel)
        }
    }

    fun updateCard(cardModel: CardModel){
        viewModelScope.launch {
            appRepository.updateCard(cardModel)
        }
    }

}