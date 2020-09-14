package com.example.notesapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.data.db.entities.NotesItem

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: NotesItem)

    @Delete
    suspend fun delete(item: NotesItem)

    @Query("SELECT * FROM notes_table")
    fun getAllNotesItem(): LiveData<List<NotesItem>>

    @Query("SELECT * FROM notes_table WHERE id = :id")
    fun getNotesItem(id: Int): NotesItem
}