package br.edu.ifsp.dmo.diario.ui.diaryEntry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.dmo.diario.data.model.DiaryEntry
import br.edu.ifsp.dmo.diario.data.repository.DiaryEntryRepository
import br.edu.ifsp.dmo.diario.util.DateConversor
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DiaryEntryViewModel(private val repository: DiaryEntryRepository) : ViewModel()  {

    private var diaryEntryId: Long = -1

    private val _saved = MutableLiveData<Boolean>()
    val saved: LiveData<Boolean> = _saved

    private val _isUpdate = MutableLiveData<Boolean>()
    val isUpdate: LiveData<Boolean> = _isUpdate

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> = _location

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _time = MutableLiveData<String>()
    val time: LiveData<String> = _time

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    init {
        _isUpdate.value = false
    }

    fun insertDiaryEntry(title: String, location: String, date: String, time: String, text: String ){

        val dateFinal: Date = DateConversor.dateFormatToDateObject(date, time)

        val diaryEntry = DiaryEntry(title = title, location = location, date = dateFinal, text = text)

        if (_isUpdate.value == false) {
            viewModelScope.launch {
                _saved.value = repository.insert(diaryEntry)
            }
        } else {
            diaryEntry.id = diaryEntryId
            viewModelScope.launch {
                _saved.value = repository.update(diaryEntry)
                diaryEntryId = -1
            }
        }
    }

    fun showEvent(id: Long) {
        viewModelScope.launch {
            val diaryEntry = repository.findById(id)
            if (diaryEntry != null){
                diaryEntryId = diaryEntry.id
                _isUpdate.value = true
                _title.value = diaryEntry.title
                _location.value = diaryEntry.location
                _date.value = DateConversor.dateFormatToTimeString(diaryEntry.date)
                _time.value = DateConversor.dateFormatToTimeString(diaryEntry.date)
                _text.value = diaryEntry.text
            }
        }
    }

}