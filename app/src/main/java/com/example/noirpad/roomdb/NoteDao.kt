package com.example.noirpad.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

//@Dao
//
//Marks this interface as a Room Data Access Object.
//Room will generate the actual code behind the scenes.

@Dao
interface NoteDao {
    //Adds a new note to your notes_table
    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

//@Query("SELECT * FROM notes_table")
//Runs a query to fetch all rows from notes_table.
//Returns them as a LiveData<List<Note>>.
//LiveData means:
//Your UI (e.g., a RecyclerView) observes this list.

    //Whenever you insert/delete a note, the UI updates automatically.
    @Query("Select * from notes_table")
    fun getAllNotes(): LiveData<List<Note>>
}
