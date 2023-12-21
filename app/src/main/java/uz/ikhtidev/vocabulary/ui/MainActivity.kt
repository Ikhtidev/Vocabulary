package uz.ikhtidev.vocabulary.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import uz.ikhtidev.vocabulary.R
import uz.ikhtidev.vocabulary.adapter.VocabularyAdapter
import uz.ikhtidev.vocabulary.databinding.ActivityMainBinding
import uz.ikhtidev.vocabulary.db.VocabularyDatabase
import uz.ikhtidev.vocabulary.ui.fragments.EditDialog
import uz.ikhtidev.vocabulary.ui.vm.MainViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val myDatabase: VocabularyDatabase by lazy {
        VocabularyDatabase.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = VocabularyAdapter(
            onItemEditClick = { vocabulary ->
                val editDialog = EditDialog()

                val bundle = Bundle()
                bundle.putInt(getString(R.string.vocabularyid), vocabulary.id)
                editDialog.arguments = bundle

                editDialog.show(supportFragmentManager, getString(R.string.tag))
            },
            onItemDeleteClick = { vocabulary ->
                myDatabase.vocabularyDao().deleteVocabulary(vocabulary)
            },
            onItemDeleteDismiss = { position ->
                binding.rvVocabulary.scrollToPosition(position)
            }
        )

        binding.apply {
            rvVocabulary.adapter = adapter
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

        viewModel.getVocabularies().observe(this) { vocabularies ->
            if (vocabularies.isEmpty()) {
                binding.apply {
                    tvEmpty.visibility = View.VISIBLE
                }
            } else {
                binding.tvEmpty.visibility = View.GONE
            }
            adapter.setData(vocabularies)
        }
    }
}
