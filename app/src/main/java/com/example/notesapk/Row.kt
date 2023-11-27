package com.example.notesapk

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class row (
    @PrimaryKey(autoGenerate = true)
    val notesid:Int,
    @ColumnInfo(name = "title") val notetiitle:String?,
    @ColumnInfo(name="content") val notescontent:String?
)