package br.edu.ifsp.dmo.diario.ui.listeners

interface DiaryEntryItemClickListener {
    fun clickOpen(position: Int)

    fun clickDeleteDiaryEntry(position: Int)
}