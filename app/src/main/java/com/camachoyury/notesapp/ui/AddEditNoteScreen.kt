package com.camachoyury.notesapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.camachoyury.notesapp.ui.data.Note
import com.camachoyury.notesapp.ui.theme.*
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    note: Note? = null,
    onBackClick: () -> Unit,
    onSaveNote: (String, String, Color, String) -> Unit,
    onDeleteNote: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var title: String by remember { mutableStateOf(note?.title ?: "") }
    var content: String by remember { mutableStateOf(note?.content ?: "") }
    var selectedColor: Color by remember { mutableStateOf(note?.backgroundColor ?: NoteCardPurple) }
    var category: String by remember { mutableStateOf(note?.category ?: "") }
    var showDeleteDialog: Boolean by remember { mutableStateOf(false) }
    var showDiscardDialog: Boolean by remember { mutableStateOf(false) }

    val isEditing = note != null
    val hasChanges = remember(title, content, selectedColor, category, note) {
        if (note == null) {
            title.isNotBlank() || content.isNotBlank()
        } else {
            title != note.title ||
                    content != note.content ||
                    selectedColor != note.backgroundColor ||
                    category != note.category
        }
    }

    val colorOptions = remember {
        listOf(
            NoteCardPurple,
            NoteCardPink,
            NoteCardGreen,
            NoteCardYellow,
            NoteCardCyan,
            NoteCardBlue
        )
    }

    val categoryOptions = remember {
        listOf("Design", "Books", "Anime", "Manga", "Social", "Development", "General")
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(NotesBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (hasChanges) {
                            showDiscardDialog = true
                        } else {
                            onBackClick()
                        }
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(ButtonBackground)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = TextWhite,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Text(
                    text = if (isEditing) "Edit Note" else "New Note",
                    color = TextWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (isEditing && onDeleteNote != null) {
                        IconButton(
                            onClick = { showDeleteDialog = true },
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFFF6B6B))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Delete",
                                tint = TextWhite,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    IconButton(
                        onClick = {
                            onSaveNote(title, content, selectedColor, category)
                        },
                        enabled = title.isNotBlank(),
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (title.isNotBlank()) NoteCardGreen else ButtonBackground)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Save",
                            tint = if (title.isNotBlank()) TextBlack else TextWhite,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Note Content Area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(selectedColor)
                    .padding(24.dp)
                    .weight(1f)
            ) {
                // Title Input
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("Note title", color = TextBlack.copy(alpha = 0.6f)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextBlack
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = TextBlack.copy(alpha = 0.3f),
                        unfocusedBorderColor = TextBlack.copy(alpha = 0.1f),
                        cursorColor = TextBlack,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    singleLine = false,
                    maxLines = 3
                )

                // Content Input
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    placeholder = {
                        Text(
                            "Start writing your note...",
                            color = TextBlack.copy(alpha = 0.6f)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = TextBlack
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = TextBlack.copy(alpha = 0.3f),
                        unfocusedBorderColor = TextBlack.copy(alpha = 0.1f),
                        cursorColor = TextBlack,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    maxLines = Int.MAX_VALUE
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Category Selector
                Column {
                    Text(
                        text = "Category",
                        color = TextBlack,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(categoryOptions) { categoryOption ->
                            FilterChip(
                                onClick = {
                                    category =
                                        if (category == categoryOption) "" else categoryOption
                                },
                                label = { Text(categoryOption) },
                                selected = category == categoryOption,
                                enabled = true,
                                colors = FilterChipDefaults.filterChipColors(
                                    containerColor = Color.Transparent,
                                    selectedContainerColor = TextBlack.copy(alpha = 0.2f),
                                    labelColor = TextBlack,
                                    selectedLabelColor = TextBlack
                                ),
                                border = FilterChipDefaults.filterChipBorder(
                                    borderColor = TextBlack.copy(alpha = 0.3f),
                                    selectedBorderColor = TextBlack.copy(alpha = 0.5f),
                                    borderWidth = 1.dp,
                                    selectedBorderWidth = 2.dp
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Color Selector
            Column {
                Text(
                    text = "Note Color",
                    color = TextWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(colorOptions) { color ->
                        ColorOption(
                            color = color,
                            isSelected = selectedColor == color,
                            onClick = { selectedColor = color }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // Delete Confirmation Dialog
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Note") },
            text = { Text("Are you sure you want to delete this note? This action cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        onDeleteNote?.invoke()
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Discard Changes Dialog
    if (showDiscardDialog) {
        AlertDialog(
            onDismissRequest = { showDiscardDialog = false },
            title = { Text("Discard Changes") },
            text = { Text("You have unsaved changes. Are you sure you want to discard them?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDiscardDialog = false
                        onBackClick()
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)
                ) {
                    Text("Discard")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDiscardDialog = false }) {
                    Text("Keep Editing")
                }
            }
        )
    }
}

@Composable
fun ColorOption(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(if (isSelected) TextWhite else Color.Transparent)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            onClick = onClick,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            color = color
        ) {
            if (isSelected) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = TextBlack,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddEditNoteScreenPreview() {
    NotesAppTheme {
        AddEditNoteScreen(
            onBackClick = { },
            onSaveNote = { _, _, _, _ -> }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditNoteScreenPreview() {
    NotesAppTheme {
        AddEditNoteScreen(
            note = Note(
                id = "1",
                title = "Sample Note",
                content = "This is a sample note content to demonstrate the editing functionality.",
                backgroundColor = NoteCardPurple,
                textColor = TextBlack,
                createdAt = LocalDateTime.now().minusDays(2),
                updatedAt = LocalDateTime.now().minusHours(3),
                category = "Design"
            ),
            onBackClick = { },
            onSaveNote = { _, _, _, _ -> },
            onDeleteNote = { }
        )
    }
}