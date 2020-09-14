package com.example.notesapp.data.repositories

import com.example.notesapp.data.db.NotesDatabase
import com.example.notesapp.data.db.entities.NotesItem

class NotesRepository(
    private val db: NotesDatabase
) {

    suspend fun upsert(item: NotesItem) = db.getNotesDao().upsert(item)

    suspend fun delete(item: NotesItem) = db.getNotesDao().delete(item)

    fun getAllNotesItem() = db.getNotesDao().getAllNotesItem()
}