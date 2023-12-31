package uz.ikhtidev.vocabulary.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.ikhtidev.vocabulary.db.entity.Vocabulary

@Dao
interface VocabularyDao {

    @Insert
    fun addVocabulary(vocabulary: Vocabulary)

    @Query("SELECT * FROM vocabulary_table")
    fun getAllVocabularies(): LiveData<List<Vocabulary>>

    @Query("SELECT * FROM vocabulary_table WHERE id = :id")
    fun getVocabularyById(id: Int) : Vocabulary

    @Update
    fun updateVocabulary(vocabulary:Vocabulary)

    @Delete
    fun deleteVocabulary(vocabulary: Vocabulary)
}