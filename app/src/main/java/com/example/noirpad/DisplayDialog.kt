package com.example.noirpad

import android.app.Dialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noirpad.roomdb.Note
import com.example.noirpad.screens.ColorPicker
import com.example.noirpad.viewmodel.NoteViewModel

@Composable
//@Preview(showBackground = true)
fun DisplayDialog(viewModel: NoteViewModel,
                  showDialog: Boolean,
                  onDismiss: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Color.Blue) }
    if (showDialog){
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Enter Note") },
            text = {
                Column {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(text = "Note Title") })
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text(text = "Note Description") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ColorPicker(selectedColor = selectedColor,
                        onColorSelected ={selectedColor=it} )
                }
            },
            confirmButton = {
                Button(onClick = {
                    var note = Note(
                        0,
                        title,
                        description,
                        selectedColor.toArgb()

                    )
                    viewModel.insert(note)
                }) {
                    Text(text = "Save Note")
                }


            },
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text(text = "Cancel")
                }
            }


        )
}
}
