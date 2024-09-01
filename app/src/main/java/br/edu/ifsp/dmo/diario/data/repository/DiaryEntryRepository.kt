package br.edu.ifsp.dmo.diario.data.repository

import android.content.Context
import br.edu.ifsp.dmo.diario.data.database.AppDatabase
import br.edu.ifsp.dmo.diario.data.model.DiaryEntry

class DiaryEntryRepository(context: Context) {

    private val database = AppDatabase.AppDatabase.getInstance(context)
    private val dao = database.getDiaryEntryDao()

    suspend fun insert(diaryEntry: DiaryEntry): Boolean{
        return dao.insert(diaryEntry) > 0
    }

    suspend fun update(diaryEntry: DiaryEntry): Boolean{
        return dao.update(diaryEntry) > 0
    }

    suspend fun remove(diaryEntry: DiaryEntry): Boolean{
        return dao.delete(diaryEntry) > 0
    }

    suspend fun findAll(): List<DiaryEntry>{
        return dao.selectAll()
    }

    suspend fun findById(id: Long): DiaryEntry{
        return dao.selectById(id)
    }

}