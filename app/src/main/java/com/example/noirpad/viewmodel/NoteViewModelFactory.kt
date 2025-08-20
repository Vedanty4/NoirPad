package com.example.noirpad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.noirpad.repositor.NoteRepository
import com.example.noirpad.roomdb.Note
import kotlinx.coroutines.launch


class NoteViewModelFactory (private val repository: NoteRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>):T{
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}