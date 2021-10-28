package com.example.notes_app_room

import androidx.room.*

@Dao
interface NotesDao {
    // get all notes in Notes table
    @Query("SELECT* FROM Notes")
    fun getallnotes():List<Notes>
    @Insert
    fun Insertnote(note:Notes)
    @Update
    fun updatenote(note:Notes)
    @Delete
    fun deletenote(note:Notes)

}