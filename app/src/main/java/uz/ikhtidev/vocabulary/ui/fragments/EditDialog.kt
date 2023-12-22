package uz.ikhtidev.vocabulary.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.ikhtidev.vocabulary.R
import uz.ikhtidev.vocabulary.databinding.DialogEditBinding
import uz.ikhtidev.vocabulary.db.VocabularyDatabase
import uz.ikhtidev.vocabulary.db.entity.Vocabulary

class EditDialog: BottomSheetDialogFragment() {

    private lateinit var binding: DialogEditBinding
    private val myDatabase: VocabularyDatabase by lazy {
        VocabularyDatabase.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogEditBinding.inflate(layoutInflater, container, false)

        val id =  arguments?.getInt(getString(R.string.vocabulary_id))?: 1
        val oldVocabulary = myDatabase.vocabularyDao().getVocabularyById(id)

        binding.etEng.setText(oldVocabulary.textEng)
        binding.etUz.setText(oldVocabulary.textUz)
        binding.etSentence.setText(oldVocabulary.sentence)

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnEdit.setOnClickListener {
            val vocabulary = Vocabulary(oldVocabulary.id, binding.etEng.text.toString(),binding.etUz.text.toString(),binding.etSentence.text.toString())
            myDatabase.vocabularyDao().updateVocabulary(vocabulary)
            Toast.makeText(
                requireActivity(),
                "Vocabulary muvaffaqiyatli o'zgartirildi",
                Toast.LENGTH_SHORT
            ).show()
            this.dismiss()
        }
        return binding.root
    }
}