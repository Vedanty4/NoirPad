package com.example.noirpad.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noirpad.roomdb.Note

@Composable
fun NoteListItem(note: Note,onNoteClick: (Note) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(12.dp), colors = CardDefaults.cardColors(
            containerColor = Color(note.color)
        ), shape = RoundedCornerShape(16.dp), border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.padding(4.dp).clickable{onNoteClick( note)}
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = "${note.title}",    style = MaterialTheme.typography.titleLarge
                , maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black.copy(alpha = 0.9f)
                        ,fontSize = 18.sp, fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "${note.description}",style = MaterialTheme.typography.bodyMedium
                , maxLines = Int.MAX_VALUE,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black.copy(alpha = 0.8f),fontWeight = FontWeight.Bold, fontSize = 16.sp
            )
        }
    }
}