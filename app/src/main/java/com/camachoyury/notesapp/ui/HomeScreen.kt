package com.camachoyury.notesapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun HomeScreen(
    onNoteClick: (Note) -> Unit = {},
    onAddNoteClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onInfoClick: () -> Unit = {}
) {
    val notes = remember {
        getSampleNotesForHomeScreen()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NotesBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Header with title and buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Notes",
                    color = TextWhite,
                    fontSize = 43.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    IconButton(
                        onClick = onSearchClick,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(ButtonBackground)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = TextWhite,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    IconButton(
                        onClick = onInfoClick,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(ButtonBackground)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info",
                            tint = TextWhite,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Note cards using LazyColumn
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(bottom = 88.dp)
            ) {
                items(notes) { note ->
                    NoteCard(
                        note = note,
                        onClick = { onNoteClick(note) }
                    )
                }
            }
        }

        // Floating Add Button
        FloatingActionButton(
            onClick = onAddNoteClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            containerColor = ButtonBackground,
            contentColor = TextWhite,
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Note",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(
    note: Note,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 100.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = note.backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = note.title,
                color = note.textColor,
                fontSize = 25.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 30.sp,
                textAlign = TextAlign.Start
            )
        }
    }
}

private fun getSampleNotesForHomeScreen(): List<Note> {
    return listOf(
        Note(
            id = "1",
            title = "UI concepts worth existing",
            content = "Collection of innovative UI design patterns and concepts that have proven to enhance user experience.",
            backgroundColor = NoteCardPurple,
            textColor = TextBlack,
            createdAt = LocalDateTime.now().minusDays(5),
            updatedAt = LocalDateTime.now().minusDays(2),
            category = "Design"
        ),
        Note(
            id = "2",
            title = "Book Review: The Design of Everyday Things by Don Norman",
            content = "An essential read for anyone interested in design and user experience.",
            backgroundColor = NoteCardPink,
            textColor = TextBlack,
            createdAt = LocalDateTime.now().minusDays(10),
            updatedAt = LocalDateTime.now().minusDays(1),
            category = "Books"
        ),
        Note(
            id = "3",
            title = "Animes produced by Ufotable",
            content = "Ufotable is renowned for their exceptional animation quality.",
            backgroundColor = NoteCardGreen,
            textColor = TextBlack,
            createdAt = LocalDateTime.now().minusDays(7),
            updatedAt = LocalDateTime.now().minusDays(3),
            category = "Anime"
        ),
        Note(
            id = "4",
            title = "Mangas planned to read",
            content = "Reading list for upcoming manga adventures.",
            backgroundColor = NoteCardYellow,
            textColor = TextBlack,
            createdAt = LocalDateTime.now().minusDays(3),
            updatedAt = LocalDateTime.now().minusHours(12),
            category = "Manga"
        ),
        Note(
            id = "5",
            title = "Awesome tweets collection",
            content = "Curated collection of insightful and inspiring tweets.",
            backgroundColor = NoteCardCyan,
            textColor = TextBlack,
            createdAt = LocalDateTime.now().minusDays(1),
            updatedAt = LocalDateTime.now().minusHours(6),
            category = "Social"
        ),
        Note(
            id = "6",
            title = "List of free & open source apps",
            content = "Amazing free and open source applications worth exploring.",
            backgroundColor = NoteCardBlue,
            textColor = TextBlack,
            createdAt = LocalDateTime.now().minusDays(14),
            updatedAt = LocalDateTime.now().minusDays(1),
            category = "Development"
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    NotesAppTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    NotesAppTheme {
        NoteCard(
            note = Note(
                id = "1",
                title = "UI concepts worth existing",
                backgroundColor = NoteCardPurple,
                textColor = TextBlack
            ),
            onClick = { }
        )
    }
}