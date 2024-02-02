package com.example.notesapk


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Row::class], version = 1)
abstract class NoteDb:RoomDatabase() {
    abstract  fun pack():NoteDao

    companion object{
        @Volatile
        private var instance:NoteDb?=null

        fun getdatabase(context: Context) : NoteDb {
            if(instance==null){
                synchronized(this){
                    instance= Room.databaseBuilder(context.applicationContext,NoteDb::class.java,"notesapp").allowMainThreadQueries().build()
                }
            }
            return instance!!
        }
    }
}