package uz.ikhtidev.vocabulary.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.ikhtidev.vocabulary.R
import uz.ikhtidev.vocabulary.databinding.ActivityQuizBinding
import uz.ikhtidev.vocabulary.db.entity.Vocabulary
import uz.ikhtidev.vocabulary.ui.vm.MainViewModel

class QuizActivity : AppCompatActivity() {

    private val binding: ActivityQuizBinding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private var vocabularyList: List<Vocabulary> = ArrayList()
    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getVocabularies().observe(this) {
            vocabularyList = it
            createQuiz(counter)
        }


    }

    private fun createQuiz(counter: Int) {
        var randomVocabularies = vocabularyList.asSequence().shuffled().take(4).toList()
        val question = randomVocabularies[0]
        binding.apply {
            tvCounter.text = (counter + 1).toString() + "-savol"
            tvEng.text = question.textEng
            randomVocabularies = randomVocabularies.shuffled()
            btnUz1.apply {
                text = randomVocabularies[0].textUz
                setOnClickListener {
                    resultCheck(question.textUz, randomVocabularies[0].textUz)
                }
            }
            btnUz2.apply {
                text = randomVocabularies[1].textUz
                setOnClickListener {
                    resultCheck(question.textUz, randomVocabularies[1].textUz)
                }
            }
            btnUz3.apply {
                text = randomVocabularies[2].textUz
                setOnClickListener {
                    resultCheck(question.textUz, randomVocabularies[2].textUz)
                }
            }
            btnUz4.apply {
                text = randomVocabularies[3].textUz
                setOnClickListener {
                    resultCheck(question.textUz, randomVocabularies[3].textUz)
                }
            }

        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun resultCheck(trueAnswer: String, userAnswer: String) {
        setClickableToButtons(false)
        if (trueAnswer == userAnswer)
            Toast.makeText(this, getString(R.string.true_answer), Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, getString(R.string.wrong_answer, trueAnswer), Toast.LENGTH_SHORT)
                .show()

        GlobalScope.launch(Dispatchers.Main) {
            delay(1500)
            createQuiz(counter++)
            setClickableToButtons(true)
        }
    }

    private fun setClickableToButtons(isClickable: Boolean) {
        binding.btnUz1.isClickable = isClickable
        binding.btnUz2.isClickable = isClickable
        binding.btnUz3.isClickable = isClickable
        binding.btnUz4.isClickable = isClickable
    }
}



