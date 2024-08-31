package br.edu.ifsp.dmo.diario.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "diary_entry")
class DiaryEntry (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "location")
    var location: String,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "text")
    var text: String
){



}