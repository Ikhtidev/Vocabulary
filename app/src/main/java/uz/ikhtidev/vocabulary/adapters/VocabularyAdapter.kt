package uz.ikhtidev.vocabulary.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ikhtidev.vocabulary.databinding.ItemLayoutBinding
import uz.ikhtidev.vocabulary.db.model.Vocabulary

class VocabularyAdapter : RecyclerView.Adapter<VocabularyAdapter.ViewHolder>() {

    private var vocabularies: List<Vocabulary> = emptyList()

    fun setData(vocabularies: List<Vocabulary>) {
        this.vocabularies = vocabularies
    }

    class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(vocabulary: Vocabulary) {
            binding.tvEng.text = vocabulary.textEng
            binding.tvUz.text = vocabulary.textUz
            binding.tvSentence.text = "Sentence: ${vocabulary.sentence}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = vocabularies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(vocabularies[position])
    }
}