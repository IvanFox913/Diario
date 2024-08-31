package br.edu.ifsp.dmo.diario.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.dmo.diario.data.model.DiaryEntry

@Dao
interface DiaryEntryDao {

    @Insert
    suspend fun insert(diaryEntry: DiaryEntry): Long

    @Update
    suspend fun updateDiaryEntry(diaryEntry: DiaryEntry): Int

    @Delete
    suspend fun deleteDiaryEntry(diaryEntry: DiaryEntry): Int

    @Query("SELECT * FROM diary_entry ORDER BY date DESC")
    suspend fun selectAll(): List<DiaryEntry>

    @Query("SELECT * FROM diary_entry WHERE id = :id LIMIT 1")
    suspend fun selectById(id: Int): DiaryEntry
}