package com.camachoyury.notesapp.ui.data

import androidx.compose.ui.graphics.Color

data class Note(
    val id: String,
    val title: String,
    val backgroundColor: Color,
    val textColor: Color = Color.Black
)