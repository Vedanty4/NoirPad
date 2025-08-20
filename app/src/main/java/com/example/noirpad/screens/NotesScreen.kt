package com.example.noirpad.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.noirpad.viewmodel.NoteViewModel
import java.lang.reflect.Modifier

@Composable
fun NotesScreen(viewModel: NoteViewModel,modifier: Modifier) {
    val notes by viewModel.allNotes.observeAsState(emptyList())

    DisplayNotesList(
        notes = notes,
        onNoteClick = { note -> viewModel.deleteNote(note)} // 👈 delete on single tap
    )
}
