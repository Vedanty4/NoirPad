package com.example.noirpad.repositor
import androidx.lifecycle.LiveData
import com.example.noirpad.roomdb.Note
import com.example.noirpad.roomdb.NoteDao

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()


    suspend fun insertNote(note: Note) {
        return noteDao.insert(note)
    }
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)


}
