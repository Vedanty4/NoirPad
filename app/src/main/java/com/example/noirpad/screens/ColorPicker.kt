package com.example.noirpad.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import java.nio.file.WatchEvent

@Composable
fun ColorPicker(selectedColor: Color,
                  onColorSelected:(Color) -> Unit) {
    var colorlist = listOf(
        Color("#556B2F".toColorInt()), // dark olive green
        Color("#6B8E23".toColorInt()), // muted green
        Color("#708090".toColorInt()), // slate gray
        Color("#4682B4".toColorInt()), // steel blue
        Color("#8B7765".toColorInt()), // taupe
        Color("#BDB76B".toColorInt()), // dark khaki
        Color("#4B5320".toColorInt()), // army green
        Color("#2F4F4F".toColorInt()), // dark slate gray
        Color("#D2B48C".toColorInt())  // tan
    )
    LazyRow(horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth().padding(8.dp))  {
        items(colorlist){
            Box(modifier = Modifier.size(40.dp).
            padding(4.dp).clip(CircleShape).
            background(color = it)
                .border(
                    width = if(it == selectedColor)4.dp else 0.dp,
                    color = if(it == selectedColor)  Color.Black else Color.Transparent,
                    shape = CircleShape
                )
                .clickable{onColorSelected(it)})
        }

    }

}