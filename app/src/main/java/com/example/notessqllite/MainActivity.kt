package com.example.notessqllite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notessqllite.databinding.ActivityMainBinding

// MainActivity class definition
class MainActivity : AppCompatActivity() {

    // Initialize binding, database helper, and adapter variables
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: NotesDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    // onCreate method called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database helper
        db = NotesDatabaseHelper(this)

        // Initialize the adapter with data from the database
        notesAdapter = NotesAdapter(db.getAllNotes(), this)

        // Set layout manager and adapter for RecyclerView
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter

        // Set click listener for add button to open AddNoteActivity
        binding.addButton.setOnClickListener{
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    // onResume method called when the activity is resumed
    override fun onResume() {
        super.onResume()

        // Refresh adapter data with updated notes from the database
        notesAdapter.refreshData(db.getAllNotes())
    }
}
