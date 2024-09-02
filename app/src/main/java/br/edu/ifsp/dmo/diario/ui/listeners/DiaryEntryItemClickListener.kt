package br.edu.ifsp.dmo.diario.ui.listeners

interface DiaryEntryItemClickListener {
    fun clickOpenDiaryEntry(position: Long)

    fun clickDeleteDiaryEntry(position: Long)
}