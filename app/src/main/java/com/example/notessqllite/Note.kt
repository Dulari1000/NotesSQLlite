package com.example.notessqllite

// Import necessary libraries

import android.app.assist.AssistContent // Import AssistContent class
import android.icu.text.CaseMap.Title // Import Title class

// Define Note data class
data class Note(val id: Int, val title: String, val content: String)

