package uz.ikhtidev.vocabulary

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.ikhtidev.vocabulary.adapters.VocabularyAdapter
import uz.ikhtidev.vocabulary.databinding.ActivityMainBinding
import uz.ikhtidev.vocabulary.db.VocabularyDatabase

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    val adapter = VocabularyAdapter()
    private  val myDatabase: VocabularyDatabase by lazy {
        VocabularyDatabase.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val vocabularies = ArrayList(myDatabase.vocabularyDao().getAllVocabularies())
        adapter.setData(vocabularies)
        binding.rvVocabulary.adapter = adapter

        binding.apply {
            btnAdd.setOnClickListener {
                startActivity(Intent(this@MainActivity, AddActivity::class.java))
            }
            btnStart.setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "Qolganini keyingi darsda qilamiz",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }

    override fun onResume() {
        val vocabularies = ArrayList(myDatabase.vocabularyDao().getAllVocabularies())
        adapter.setData(vocabularies)
        adapter.notifyDataSetChanged()
        super.onResume()
    }

}