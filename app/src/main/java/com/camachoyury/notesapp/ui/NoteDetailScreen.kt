package com.camachoyury.notesapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Check
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
fun NoteDetailScreen(
    note: Note,
    onBackClick: () -> Unit,
    onEditNote: (String, String) -> Unit,
    onDeleteNote: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isEditing by remember { mutableStateOf(false) }
    var editTitle by remember { mutableStateOf(note.title) }
    var editContent by remember { mutableStateOf(note.content) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(note) {
        editTitle = note.title
        editContent = note.content
        isEditing = false
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
                    onClick = onBackClick,
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

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (isEditing) {
                        IconButton(
                            onClick = {
                                onEditNote(editTitle, editContent)
                                isEditing = false
                            },
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(NoteCardGreen)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Save",
                                tint = TextBlack,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { isEditing = true },
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(ButtonBackground)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit",
                                tint = TextWhite,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        IconButton(
                            onClick = { showDeleteDialog = true },
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFFF6B6B))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = TextWhite,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Note Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(note.backgroundColor)
                    .padding(24.dp)
                    .weight(1f)
            ) {
                if (isEditing) {
                    // Edit Mode
                    OutlinedTextField(
                        value = editTitle,
                        onValueChange = { editTitle = it },
                        label = { Text("Title") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = note.textColor
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = note.textColor.copy(alpha = 0.5f),
                            unfocusedBorderColor = note.textColor.copy(alpha = 0.3f),
                            focusedLabelColor = note.textColor,
                            unfocusedLabelColor = note.textColor.copy(alpha = 0.7f),
                            cursorColor = note.textColor
                        )
                    )

                    OutlinedTextField(
                        value = editContent,
                        onValueChange = { editContent = it },
                        label = { Text("Content") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            color = note.textColor
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = note.textColor.copy(alpha = 0.5f),
                            unfocusedBorderColor = note.textColor.copy(alpha = 0.3f),
                            focusedLabelColor = note.textColor,
                            unfocusedLabelColor = note.textColor.copy(alpha = 0.7f),
                            cursorColor = note.textColor
                        ),
                        maxLines = Int.MAX_VALUE
                    )
                } else {
                    // Read Mode
                    Text(
                        text = note.title,
                        color = note.textColor,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 34.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .weight(1f)
                    ) {
                        if (note.content.isNotBlank()) {
                            Text(
                                text = note.content,
                                color = note.textColor,
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                modifier = Modifier.padding(bottom = 24.dp)
                            )
                        } else {
                            Text(
                                text = "Tap the edit button to add content to this note...",
                                color = note.textColor.copy(alpha = 0.6f),
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 48.dp)
                            )
                        }
                    }
                }

                // Note metadata
                if (!isEditing) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column {
                            Text(
                                text = "Category: ${note.category}",
                                color = note.textColor.copy(alpha = 0.7f),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Created: ${note.getFormattedCreatedDate()}",
                                color = note.textColor.copy(alpha = 0.7f),
                                fontSize = 12.sp
                            )
                            if (note.updatedAt != note.createdAt) {
                                Text(
                                    text = "Updated: ${note.getFormattedUpdatedDate()}",
                                    color = note.textColor.copy(alpha = 0.7f),
                                    fontSize = 12.sp
                                )
                            }
                        }

                        // Color indicator
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(note.backgroundColor)
                        )
                    }
                }
            }
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
                        onDeleteNote()
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteDetailScreenPreview() {
    NotesAppTheme {
        NoteDetailScreen(
            note = Note(
                id = "1",
                title = "Sample Note",
                content = "This is a sample note content to demonstrate the note detail screen layout and functionality.",
                backgroundColor = NoteCardPurple,
                textColor = TextBlack,
                createdAt = LocalDateTime.now().minusDays(2),
                updatedAt = LocalDateTime.now().minusHours(3),
                category = "Sample"
            ),
            onBackClick = { },
            onEditNote = { _, _ -> },
            onDeleteNote = { }
        )
    }
}