package br.edu.ifsp.dmo.diario.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.dmo.diario.R
import br.edu.ifsp.dmo.diario.data.model.DiaryEntry
import br.edu.ifsp.dmo.diario.databinding.ItemDiaryEntryBinding
import br.edu.ifsp.dmo.diario.ui.listeners.DiaryEntryItemClickListener

class DiaryEntryAdapter(private val listener: DiaryEntryItemClickListener) : RecyclerView.Adapter<DiaryEntryAdapter.ViewHolder>() {

    private var dataset: List<DiaryEntry> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemDiaryEntryBinding = ItemDiaryEntryBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_diary_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val diaryEntry = dataset[position]


        holder.binding.textviewTitleField.text = diaryEntry.title
        holder.binding.textviewLocationField.text = diaryEntry.location

        holder.binding.buttonDelete.setOnClickListener(){listener.clickDeleteDiaryEntry(position)}
    }
    override fun getItemCount(): Int {
        return dataset.size
    }

    fun submitDataset(data: List<DiaryEntry>) {
        dataset = data
        this.notifyDataSetChanged()
    }

    fun getDatasetItem(position: Int): DiaryEntry {
        return dataset[position]
    }
}