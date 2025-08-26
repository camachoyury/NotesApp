package com.camachoyury.notesapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.camachoyury.notesapp.ui.data.Note
import com.camachoyury.notesapp.ui.theme.*
import java.time.LocalDateTime

class NotesViewModel : ViewModel() {

    var notes by mutableStateOf(getSampleNotes())
        private set

    var selectedNote by mutableStateOf<Note?>(null)
        private set

    var searchQuery by mutableStateOf("")
        private set

    val filteredNotes: List<Note>
        get() = if (searchQuery.isBlank()) {
            notes
        } else {
            notes.filter { note ->
                note.title.contains(searchQuery, ignoreCase = true) ||
                        note.content.contains(searchQuery, ignoreCase = true) ||
                        note.category.contains(searchQuery, ignoreCase = true)
            }
        }

    fun selectNote(note: Note) {
        selectedNote = note
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun addNote(
        title: String,
        content: String,
        backgroundColor: androidx.compose.ui.graphics.Color
    ) {
        val newNote = Note(
            id = generateId(),
            title = title,
            content = content,
            backgroundColor = backgroundColor,
            textColor = TextBlack,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            category = inferCategoryFromTitle(title)
        )
        notes = notes + newNote
    }

    fun updateNote(noteId: String, title: String, content: String) {
        notes = notes.map { note ->
            if (note.id == noteId) {
                note.copy(
                    title = title,
                    content = content,
                    updatedAt = LocalDateTime.now()
                )
            } else {
                note
            }
        }
        // Update selected note if it's the one being edited
        if (selectedNote?.id == noteId) {
            selectedNote = notes.find { it.id == noteId }
        }
    }

    fun deleteNote(noteId: String) {
        notes = notes.filter { it.id != noteId }
        // Clear selected note if it was deleted
        if (selectedNote?.id == noteId) {
            selectedNote = null
        }
    }

    private fun generateId(): String {
        return "note_${System.currentTimeMillis()}"
    }

    private fun inferCategoryFromTitle(title: String): String {
        return when {
            title.contains("UI", ignoreCase = true) || title.contains(
                "design",
                ignoreCase = true
            ) -> "Design"

            title.contains("book", ignoreCase = true) || title.contains(
                "review",
                ignoreCase = true
            ) -> "Books"

            title.contains("anime", ignoreCase = true) -> "Anime"
            title.contains("manga", ignoreCase = true) -> "Manga"
            title.contains("tweet", ignoreCase = true) || title.contains(
                "twitter",
                ignoreCase = true
            ) -> "Social"

            title.contains("app", ignoreCase = true) || title.contains(
                "source",
                ignoreCase = true
            ) -> "Development"

            else -> "General"
        }
    }

    private fun getSampleNotes(): List<Note> {
        return listOf(
            Note(
                id = "1",
                title = "UI concepts worth existing",
                content = "Collection of innovative UI design patterns and concepts that have proven to enhance user experience:\n\n• Progressive disclosure - showing information progressively\n• Contextual actions - actions appear when needed\n• Smart defaults - pre-filling forms intelligently\n• Gestural interfaces - intuitive touch interactions\n\nThese concepts form the foundation of modern interface design.",
                backgroundColor = NoteCardPurple,
                textColor = TextBlack,
                createdAt = LocalDateTime.now().minusDays(5),
                updatedAt = LocalDateTime.now().minusDays(2),
                category = "Design"
            ),
            Note(
                id = "2",
                title = "Book Review: The Design of Everyday Things by Don Norman",
                content = "An essential read for anyone interested in design and user experience.\n\nKey takeaways:\n• Design should be intuitive and self-explanatory\n• Affordances guide user behavior\n• Feedback is crucial for user understanding\n• Error prevention is better than error handling\n\nRating: ⭐⭐⭐⭐⭐\n\nThis book changed how I think about design in everyday objects and digital interfaces.",
                backgroundColor = NoteCardPink,
                textColor = TextBlack,
                createdAt = LocalDateTime.now().minusDays(10),
                updatedAt = LocalDateTime.now().minusDays(1),
                category = "Books"
            ),
            Note(
                id = "3",
                title = "Animes produced by Ufotable",
                content = "Ufotable is renowned for their exceptional animation quality:\n\n🗾 **Completed Series:**\n• Demon Slayer (Kimetsu no Yaiba)\n• Fate/Zero\n• Fate/stay night: Unlimited Blade Works\n• Tales of Zestiria the X\n• God Eater\n\n🎬 **Movies:**\n• Demon Slayer: Mugen Train\n• Fate/stay night: Heaven's Feel trilogy\n\nTheir blend of traditional and digital animation creates stunning visual experiences.",
                backgroundColor = NoteCardGreen,
                textColor = TextBlack,
                createdAt = LocalDateTime.now().minusDays(7),
                updatedAt = LocalDateTime.now().minusDays(3),
                category = "Anime"
            ),
            Note(
                id = "4",
                title = "Mangas planned to read",
                content = "Reading list for upcoming manga adventures:\n\n📚 **Priority List:**\n• Chainsaw Man (Part 2)\n• Spy x Family\n• Jujutsu Kaisen\n• My Hero Academia\n• Dr. Stone\n• Promised Neverland\n\n📖 **On Hold:**\n• One Piece (catching up from chapter 800)\n• Attack on Titan (final arc)\n• Berserk (continuing from where I left off)\n\nGoal: Read at least 2 series per month.",
                backgroundColor = NoteCardYellow,
                textColor = TextBlack,
                createdAt = LocalDateTime.now().minusDays(3),
                updatedAt = LocalDateTime.now().minusHours(12),
                category = "Manga"
            ),
            Note(
                id = "5",
                title = "Awesome tweets collection",
                content = "Curated collection of insightful and inspiring tweets:\n\n💡 **Tech Wisdom:**\n• \"Code is read more often than it's written\" - @unclebobmartin\n• \"Premature optimization is the root of all evil\" - @kentcdodds\n• \"Make it work, make it right, make it fast\" - @karpathy\n\n🎨 **Design Insights:**\n• \"Good design is invisible\" - @helvetica\n• \"Simplicity is the ultimate sophistication\" - @designmilk\n\n🚀 **Motivation:**\n• \"Ship early, ship often\" - @paulg\n• \"Done is better than perfect\" - @sherylsandberg",
                backgroundColor = NoteCardCyan,
                textColor = TextBlack,
                createdAt = LocalDateTime.now().minusDays(1),
                updatedAt = LocalDateTime.now().minusHours(6),
                category = "Social"
            ),
            Note(
                id = "6",
                title = "List of free & open source apps",
                content = "Amazing free and open source applications worth exploring:\n\n🔧 **Development Tools:**\n• VS Code - Microsoft's free code editor\n• IntelliJ IDEA Community - JetBrains IDE\n• Git - Version control system\n• Docker - Containerization platform\n\n📱 **Mobile Apps:**\n• F-Droid - Open source app store\n• Signal - Private messaging\n• NewPipe - YouTube client\n• Simple Mobile Tools suite\n\n🎨 **Creative Tools:**\n• GIMP - Image editor\n• Blender - 3D modeling\n• Krita - Digital painting\n• Audacity - Audio editing",
                backgroundColor = NoteCardBlue,
                textColor = TextBlack,
                createdAt = LocalDateTime.now().minusDays(14),
                updatedAt = LocalDateTime.now().minusDays(1),
                category = "Development"
            )
        )
    }
}