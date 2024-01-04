package de.syntax_institut.funappsvorlage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import de.syntax_institut.funappsvorlage.R
import de.syntax_institut.funappsvorlage.data.model.Meme
import de.syntax_institut.funappsvorlage.databinding.ListItemMemeBinding
import de.syntax_institut.funappsvorlage.ui.MemesViewModel

/**
 * Diese Klasse organisiert mithilfe der ViewHolder Klasse das Recycling
 */
class MemeAdapter(
    private val dataSet: List<Meme>,
    private val viewModel: MemesViewModel
) : RecyclerView.Adapter<MemeAdapter.ItemViewHolder>() {

    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class ItemViewHolder(val binding: ListItemMemeBinding) :
        RecyclerView.ViewHolder(binding.root)

    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ListItemMemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    /**
     * hier findet der Recyclingprozess statt
     * die vom ViewHolder bereitgestellten Parameter erhalten die Information des Listeneintrags
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val meme = dataSet[position]
        val titleText = if(meme.savedTitle != "") meme.savedTitle
        else meme.name

        holder.binding.tvMeme.text = titleText

        holder.binding.ivMeme.load(meme.url) {
            error(R.drawable.ic_broken_image)
            placeholder(R.drawable.ic_launcher_background)
        }

        holder.binding.btnSave.setOnClickListener {
            val title = holder.binding.etTitle.text.toString()
            holder.binding.etTitle.setText("")
            holder.binding.tvMeme.text = title
            meme.savedTitle = title
            viewModel.saveTitle(meme.id, title)
        }
    }

    /**
     * damit der LayoutManager weiß, wie lang die Liste ist
     */
    override fun getItemCount(): Int {
        return dataSet.size
    }
}
