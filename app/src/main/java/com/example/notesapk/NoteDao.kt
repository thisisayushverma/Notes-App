package com.example.notesapk

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert
    fun insert(row: Row)

    @Delete
    fun delete(row: Row)

    @Query("SELECT * FROM tableone ORDER BY notedid DESC"  )
    fun getalldata():List<Row>

    @Query("SELECT MAX(notedid) FROM tableone")
    fun getlatdata():Int

    @Query("SELECT * FROM tableone WHERE notedid=:id")
    fun getpartdata(id:Int):Row

    @Query("DELETE FROM tableone WHERE notedid =:id")
    fun delnote(id:Int)
    @Query("UPDATE tableone SET notetitle=(:title), notecontent=:content WHERE notedid=:id")
    fun updatedata(id:Int,title:String,content:String,)
}