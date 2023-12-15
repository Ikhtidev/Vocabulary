package uz.ikhtidev.vocabulary.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.ikhtidev.vocabulary.db.model.Vocabulary

@Dao
interface VocabularyDao {

    @Insert
    fun addVocabulary(vocabulary: Vocabulary)

    @Query("select * from vocabulary_table")
    fun getAllVocabularies(): List<Vocabulary>

}