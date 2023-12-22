package uz.ikhtidev.vocabulary.adapter


import android.annotation.SuppressLint
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import uz.ikhtidev.vocabulary.MyApp
import uz.ikhtidev.vocabulary.R
import uz.ikhtidev.vocabulary.databinding.LayoutVocabularyBinding
import uz.ikhtidev.vocabulary.db.entity.Vocabulary

class VocabularyAdapter(
    private val onItemEditClick: (Vocabulary) -> Unit,
    private val onItemDeleteClick: (Vocabulary) -> Unit,
    private val onItemDeleteDismiss: (Int) -> Unit
) : RecyclerView.Adapter<VocabularyAdapter.ViewHolder>() {

    private var vocabularies: ArrayList<Vocabulary> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newVocabularies: List<Vocabulary>) {
        vocabularies = newVocabularies as ArrayList<Vocabulary>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: LayoutVocabularyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(vocabulary: Vocabulary) {
            binding.tvEng.text = vocabulary.textEng
            binding.tvUz.text = vocabulary.textUz
            binding.tvSentence.text =
                MyApp.getContext().getString(R.string.sentence, vocabulary.sentence)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutVocabularyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = vocabularies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vocabulary = vocabularies[position]
        holder.onBind(vocabulary)
        holder.binding.apply {
            cardItem.setOnClickListener {
                if (settingsLayout.isGone) {
                    settingsLayout.visibility = View.VISIBLE
                    TransitionManager.beginDelayedTransition(fullItem)
                } else {
                    settingsLayout.visibility = View.GONE
                    TransitionManager.beginDelayedTransition(someLayout)
                }
            }
            btnEdit.setOnClickListener {
                onItemEditClick(vocabulary)
            }
            btnDelete.setOnClickListener {

                val snackBar = Snackbar.make(
                    it,
                    MyApp.getContext().getString(R.string.vocabulary_deleted), Snackbar.LENGTH_LONG
                )
                snackBar.setAction("Undo") {
                    vocabularies.add(position, vocabulary)
                    notifyItemInserted(position)
                    onItemDeleteDismiss(position)
                }
                snackBar.addCallback(object : Snackbar.Callback() {
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                        if (event != DISMISS_EVENT_ACTION) {
                            onItemDeleteClick(vocabulary)
                        }
                    }
                })
                snackBar.show()

                vocabularies.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }
}