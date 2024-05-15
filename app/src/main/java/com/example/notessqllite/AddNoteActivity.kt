package com.example.notessqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notessqllite.databinding.ActivityAddNoteBinding

// Import the binding class for the activity
import com.example.notessqllite.databinding.ActivityMainBinding

// Import the NotesDatabaseHelper class
import com.example.notessqllite.NotesDatabaseHelper

// AddNoteActivity class definition
class AddNoteActivity : AppCompatActivity() {

    // Initialize binding and database helper variables
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NotesDatabaseHelper

    // onCreate method called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database helper
        db = NotesDatabaseHelper(this)

        // Set click listener for save button
        binding.saveButton.setOnClickListener{
            // Get title and content from EditText fields
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()

            // Create Note object with title and content
            val note = Note(0,title,content)

            // Insert note into database
            db.insertNote(note)

            // Finish activity and display toast message
            finish()
            Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show()
        }
    }
}
