package com.example.notesapk

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableone")
data class Row(
    @PrimaryKey(autoGenerate = true)
    val notedid:Int=0,
    @ColumnInfo
    val notetitle:String,
    @ColumnInfo
    val notecontent:String

)
