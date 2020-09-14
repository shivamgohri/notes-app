package com.example.notesapp.ui.noteslist

import androidx.lifecycle.ViewModel
import com.example.notesapp.data.db.entities.NotesItem
import com.example.notesapp.data.repositories.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesListViewModel(
    private val repository: NotesRepository
): ViewModel() {

    fun upsert(item: NotesItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: NotesItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllNotesItem() = repository.getAllNotesItem()
}