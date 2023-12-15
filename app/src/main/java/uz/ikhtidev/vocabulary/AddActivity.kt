package uz.ikhtidev.vocabulary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.ikhtidev.vocabulary.databinding.ActivityAddBinding
import uz.ikhtidev.vocabulary.db.VocabularyDatabase
import uz.ikhtidev.vocabulary.db.model.Vocabulary

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private val myDatabase: VocabularyDatabase by lazy {
        VocabularyDatabase.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            submitButtonClicked(
                binding.etEng.text.toString().trim(),
                binding.etEng.text.toString().trim(),
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
            Toast.makeText(this, "Lug'at qo'shildi!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Iltimos barcha maydonlarni to'ldiring!", Toast.LENGTH_SHORT)
                .show()
        }
    }
}