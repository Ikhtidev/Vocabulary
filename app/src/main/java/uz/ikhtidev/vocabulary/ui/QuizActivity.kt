package uz.ikhtidev.vocabulary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import uz.ikhtidev.vocabulary.databinding.ActivityQuizBinding
import uz.ikhtidev.vocabulary.db.VocabularyDatabase
import uz.ikhtidev.vocabulary.db.entity.Vocabulary
import uz.ikhtidev.vocabulary.ui.vm.MainViewModel

class QuizActivity : AppCompatActivity() {

    private val binding:ActivityQuizBinding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }

    private val myDatabase: VocabularyDatabase by lazy {
        VocabularyDatabase.getInstance(this)
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    var vocabularyList = emptyList<Vocabulary>()
    var counter:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getVocabularies().observe(this) { vocabularies ->
            vocabularyList = vocabularies
        }

        initViews(counter)
    }

    private fun initViews(counter: Int) {
        val vocabulary = vocabularyList[counter]

        val randomVocabularies = vocabularyList.asSequence().shuffled().take(4).toList()
        val question = randomVocabularies[0]
//        binding.tvEng.text = vocabu
    }
}