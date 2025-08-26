package com.camachoyury.notesapp.ui.data

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Note(
    val id: String,
    val title: String,
    val content: String = "",
    val backgroundColor: Color,
    val textColor: Color = Color.Black,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val category: String = ""
) {
    fun getFormattedCreatedDate(): String {
        return createdAt.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
    }

    fun getFormattedUpdatedDate(): String {
        return updatedAt.format(DateTimeFormatter.ofPattern("MMM dd, yyyy • HH:mm"))
    }
}