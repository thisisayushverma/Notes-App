package com.example.notesapk

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface NoteDao {
    @Insert
    fun insert(row: row)

    @Delete
    fun delete(row: row)

}