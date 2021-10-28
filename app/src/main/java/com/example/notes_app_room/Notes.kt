package com.example.notes_app_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Notes")

data class Notes(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") val id:Int = 0,
    @ColumnInfo(name = "Note") val Note:String
)



