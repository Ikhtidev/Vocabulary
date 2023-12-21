package uz.ikhtidev.vocabulary.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import uz.ikhtidev.vocabulary.MyApp
import uz.ikhtidev.vocabulary.db.VocabularyDatabase
import uz.ikhtidev.vocabulary.db.entity.Vocabulary

class MainViewModel:ViewModel() {
    private val myDao = VocabularyDatabase.getInstance(MyApp.getContext()).vocabularyDao()

    fun getVocabularies():LiveData<List<Vocabulary>>{
        return myDao.getAllVocabularies()
    }

}