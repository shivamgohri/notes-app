package com.example.notesapp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NotesItem(
    @ColumnInfo(name = "item_title")
    var title: String,
    @ColumnInfo(name = "item_note")
    var note: String,
    @ColumnInfo(name = "item_info")
    var time: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}