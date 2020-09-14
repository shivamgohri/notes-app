package com.example.notesapp.ui.noteslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.data.db.NotesDatabase
import com.example.notesapp.data.db.entities.NotesItem
import com.example.notesapp.data.repositories.NotesRepository
import com.example.notesapp.other.NotesItemAdapter
import kotlinx.android.synthetic.main.activity_noteslist.*

class NotesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noteslist)

        val database = NotesDatabase(this)
        val repository = NotesRepository(database)
        val factory = NotesListViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory).get(NotesListViewModel::class.java)

        val adapter = NotesItemAdapter(listOf(), viewModel)

        rvNotesList.layoutManager = LinearLayoutManager(this)
        rvNotesList.adapter = adapter

        viewModel.getAllNotesItem().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddNotesItemDialog(this,
            object: AddNotesDialogListener {
                override fun onAddButtonClicked(item: NotesItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}