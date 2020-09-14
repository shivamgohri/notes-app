package com.example.notesapp

import android.app.Application
import com.example.notesapp.data.db.NotesDatabase
import com.example.notesapp.data.repositories.NotesRepository
import com.example.notesapp.ui.noteslist.NotesListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class NotesApplication: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@NotesApplication))
        bind() from singleton { NotesDatabase(instance()) }
        bind() from singleton { NotesRepository(instance()) }
        bind() from provider {
            NotesListViewModelFactory(instance())
        }
    }
}