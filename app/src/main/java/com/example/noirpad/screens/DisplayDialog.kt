package com.example.noirpad.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.noirpad.roomdb.Note
import com.example.noirpad.viewmodel.NoteViewModel

@Composable
fun DisplayDialog(
    viewModel: NoteViewModel,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Color(0xFFBBDEFB)) }

    if (showDialog) {
        Dialog(onDismissRequest = {
            // clear when dismissed
            title = ""
            description = ""
            onDismiss()
        }) {
            Card(
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    // Dialog title
                    Text(
                        "Add Note",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Title field
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Note Title", style = MaterialTheme.typography.bodyMedium) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Description field
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Note Description", style = MaterialTheme.typography.bodyMedium) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        shape = RoundedCornerShape(12.dp),
                        maxLines = 5
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Color picker label
                    Text(
                        "Pick Color:",
                        style = MaterialTheme.typography.titleSmall
                    )
                    ColorPicker(
                        selectedColor = selectedColor,
                        onColorSelected = { selectedColor = it }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Buttons
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = {
                            // clear when cancel
                            title = ""
                            description = ""
                            onDismiss()
                        }) {
                            Text("Cancel", style = MaterialTheme.typography.bodyMedium)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            if (title.isNotBlank() && description.isNotBlank()) {
                                val note = Note(
                                    id = 0,
                                    title = title,
                                    description = description,
                                    color = selectedColor.toArgb()
                                )
                                viewModel.insert(note)

                                // clear after save
                                title = ""
                                description = ""
                                selectedColor = Color(0xFFBBDEFB)

                                onDismiss()
                            }
                        }) {
                            Text("Save Note", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
