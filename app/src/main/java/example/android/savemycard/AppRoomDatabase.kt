package example.android.savemycard

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CardModel::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun dao() : Dao

    companion object{

        @Volatile
        private var INSTANCE : AppRoomDatabase? = null

        fun getDatabase(context : Context): AppRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppRoomDatabase::class.java,
                    "card_database")
                    .build()
                INSTANCE = instance
                return instance
            }

        }

    }

}