package uz.ikhtidev.vocabulary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.ikhtidev.vocabulary.db.dao.VocabularyDao
import uz.ikhtidev.vocabulary.db.model.Vocabulary

@Database(entities = [Vocabulary::class], version = 1)
abstract class VocabularyDatabase : RoomDatabase() {

    abstract fun vocabularyDao(): VocabularyDao

    companion object {
        private var INSTANCE: VocabularyDatabase? = null

        @Synchronized
        fun getInstance(context: Context): VocabularyDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, VocabularyDatabase::class.java, "vocabulary_database")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}