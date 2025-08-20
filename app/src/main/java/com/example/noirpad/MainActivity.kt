package com.example.noirpad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import com.example.noirpad.repositor.NoteRepository
import com.example.noirpad.roomdb.Note
import com.example.noirpad.roomdb.NoteDB
import com.example.noirpad.screens.DisplayDialog
import com.example.noirpad.screens.NotesScreen
import com.example.noirpad.ui.theme.NoirPadTheme
import com.example.noirpad.viewmodel.NoteViewModel
import com.example.noirpad.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val database = NoteDB.getInstance(applicationContext)
        val repository = NoteRepository(database.noteDao)
        val viwModelFactory = NoteViewModelFactory(repository)
        val noteeViewModel = ViewModelProvider(
            this, viwModelFactory
        )[NoteViewModel::class.java]
        val note1 = Note(
            0, "This is a Demo Note ", "Done coding callenges for today",
            "#f59597".toColorInt()

        )
        noteeViewModel.insert(note1) // 👈 moved out

        setContent {
            NoirPadTheme {
                Scaffold(
                    topBar = { NoirPadTopBar() }, //

                    floatingActionButton = { MyFAB(viewModel = noteeViewModel) },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color(0xFFF5F5DC),
                                    Color(0xFFE6E6FA)
                                )
                            )
                        )
                ) { innerPadding ->   // 👈 important
                    val notes by noteeViewModel.allNotes.observeAsState(emptyList())

                    NotesScreen(
                        viewModel = noteeViewModel,
                        modifier = Modifier
                            .padding(innerPadding) // 👈 pushes content below TopAppBar
                    )
                }


            }
        }

    }
}
@Composable
fun NoirPadTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp) // same as Material top app bar height
            .background(Color(0xFFA199AF)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "NoirPad",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge
        )
    }
}


@Composable
fun MyFAB(viewModel: NoteViewModel){
    var showDialog by remember {
        mutableStateOf(false)
    }
    DisplayDialog(
        viewModel = viewModel,
        showDialog = showDialog
    ) {
        showDialog = false
    }
    FloatingActionButton(
        onClick = { showDialog = true },
        containerColor = Color.Blue,
        contentColor = Color.White

    ) {
        Icon(imageVector = Icons.Filled.Add,
            contentDescription = "Add Note")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoirPadTheme {
    }
}