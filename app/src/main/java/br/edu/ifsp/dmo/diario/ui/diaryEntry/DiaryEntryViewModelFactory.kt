package br.edu.ifsp.dmo.diario.ui.diaryEntry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.diario.data.repository.DiaryEntryRepository
import br.edu.ifsp.dmo.diario.ui.main.MainViewModel

class DiaryEntryViewModelFactory (private val repository: DiaryEntryRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(DiaryEntryViewModel::class.java)) {
            return DiaryEntryViewModel(repository) as T
        }

        throw IllegalArgumentException("View Model desconhecido")
    }
}