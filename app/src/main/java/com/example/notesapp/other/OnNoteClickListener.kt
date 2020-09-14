package com.example.notesapp.other

import com.example.notesapp.data.db.entities.NotesItem

interface OnNoteClickListener {

    fun OnNoteClick(item: NotesItem)
}