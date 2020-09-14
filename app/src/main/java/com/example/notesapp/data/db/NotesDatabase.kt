package com.example.notesapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.data.db.entities.NotesItem

@Database(
    entities = [NotesItem::class],
    version = 1
)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object{
        @Volatile
        private var instance: NotesDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                NotesDatabase::class.java, "NotesDB.db").build()
    }
}