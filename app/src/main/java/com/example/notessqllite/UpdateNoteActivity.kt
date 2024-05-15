package com.example.notessqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notessqllite.databinding.ActivityUpdateNoteBinding

// Activity for updating a note
class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId: Int = -1

    // Initialize the activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database helper
        db = NotesDatabaseHelper(this)

        // Retrieve the note ID from the intent
        noteId = intent.getIntExtra("note_id", -1)
        // If note ID is invalid, finish the activity
        if (noteId == -1) {
            finish()
            return
        }

        // Retrieve the note from the database
        val note = db.getNoteById(noteId)
        // Set the title and content of the note in the UI
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        // Set click listener for the save button
        binding.updateSaveButton.setOnClickListener {
            // Get the updated title and content from the UI
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            // Create an updated note object
            val updatedNote = Note(noteId, newTitle, newContent)
            // Update the note in the database
            db.updateNote(updatedNote)
            // Finish the activity
            finish()
            // Display a toast message to indicate that changes are saved
            Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show()
        }
    }
}
