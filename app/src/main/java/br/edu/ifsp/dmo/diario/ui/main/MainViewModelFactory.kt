package br.edu.ifsp.dmo.diario.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.diario.data.repository.DiaryEntryRepository

class MainViewModelFactory (private val repository: DiaryEntryRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("View Model desconhecido")
    }
}