package uz.ikhtidev.vocabulary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.ikhtidev.vocabulary.R
import uz.ikhtidev.vocabulary.databinding.ActivityAddBinding
import uz.ikhtidev.vocabulary.db.VocabularyDatabase
import uz.ikhtidev.vocabulary.db.entity.Vocabulary

class AddActivity : AppCompatActivity() {

    private val binding: ActivityAddBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }
    private val myDatabase: VocabularyDatabase by lazy {
        VocabularyDatabase.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            submitButtonClicked(
                binding.etEng.text.toString().trim(),
                binding.etUz.text.toString().trim(),
                binding.etSentence.text.toString().trim()
            )
        }
    }

    private fun submitButtonClicked(textEnglish: String, textUzbek: String, textSentence: String) {
        if (textEnglish.isNotBlank() && textUzbek.isNotBlank() && textSentence.isNotBlank()) {
            myDatabase.vocabularyDao().addVocabulary(
                Vocabulary(
                    textEng = textEnglish,
                    textUz = textUzbek,
                    sentence = textSentence
                )
            )
            Toast.makeText(this, getString(R.string.vocabulary_added), Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, getString(R.string.requirement_fill_blanks), Toast.LENGTH_SHORT)
                .show()
        }
    }
}