package br.edu.ifsp.dmo.diario.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.dmo.diario.data.model.DiaryEntry
import br.edu.ifsp.dmo.diario.data.repository.DiaryEntryRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DiaryEntryRepository) : ViewModel() {

    private val _diaryEntries = MutableLiveData<List<DiaryEntry>>()
    val diaryEntries: LiveData<List<DiaryEntry>> = _diaryEntries

    init {
        checkDatabase()
    }

    fun checkDatabase() {
        viewModelScope.launch {
            val list = repository.findAll()
            _diaryEntries.postValue(list)
        }
    }

    fun deleteDiaryEntry(id: Long) {
        viewModelScope.launch {
            val diaryEntry = repository.findById(id)
            if (diaryEntry != null) {
                repository.remove(diaryEntry)
                checkDatabase()
            }
        }
    }
}