package com.example.notesapp.ui.noteslist

import com.example.notesapp.data.db.entities.NotesItem

interface AddNotesDialogListener {

    fun onAddButtonClicked(item: NotesItem)
}