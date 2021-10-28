package com.example.notes_app_room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Notes is name of table
@Database(entities = [Notes::class],version = 1,exportSchema = false)
abstract class NotesDatabase:RoomDatabase() {
    companion object{
        var instance:NotesDatabase?=null
        fun getinstance(context: Context):NotesDatabase
        {
            if (instance!= null)
            {
                return instance as NotesDatabase
            }
            // Details is name of database


            instance = Room.databaseBuilder(context,NotesDatabase::class.java,"Details").run { allowMainThreadQueries()}.build()
            return instance as NotesDatabase
    }

}
    abstract fun NotesDao():NotesDao
}