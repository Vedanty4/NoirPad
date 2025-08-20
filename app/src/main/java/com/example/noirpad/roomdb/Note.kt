package com.example.noirpad.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey


//@Entity(tableName = "notes_table")
//
//This tells Room:
//👉 "Hey, I want to create a table in the SQLite database.
//👉 The table’s name will be notes_table."
//
//So every row in this table will represent one Note
@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val color: Int
)