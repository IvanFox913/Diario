package br.edu.ifsp.dmo.diario.ui.listeners

interface DiaryEntryItemClickListener {
    fun clickOpenDiaryEntry(position: Int)

    fun clickDeleteDiaryEntry(position: Long)
}