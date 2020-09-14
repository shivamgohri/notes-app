package com.example.notesapp.ui.noteslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.data.repositories.NotesRepository

class NotesListViewModelFactory(
    private val repository: NotesRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesListViewModel(repository) as T
    }
}