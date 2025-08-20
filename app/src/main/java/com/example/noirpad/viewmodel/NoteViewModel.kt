package com.example.noirpad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noirpad.repositor.NoteRepository
import com.example.noirpad.roomdb.Note
import kotlinx.coroutines.launch

class NoteViewModel (private val repository: NoteRepository):
    ViewModel() {
    val allNotes: LiveData<List<Note>> = repository.allNotes
    fun insert(note:Note){
        viewModelScope.launch {
            repository.insertNote(note)
        }

    }


    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }


}