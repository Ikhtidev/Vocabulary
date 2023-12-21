package uz.ikhtidev.vocabulary.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocabulary_table")
data class Vocabulary(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "text_eng")
    var textEng: String,
    @ColumnInfo(name = "text_uz")
    var textUz: String,
    @ColumnInfo(name = "sentence")
    var sentence: String
)
