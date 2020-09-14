package com.example.notesapp.ui.noteslist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.data.db.entities.NotesItem
import com.example.notesapp.other.NotesItemAdapter
import com.example.notesapp.other.OnNoteClickListener
import com.example.notesapp.ui.notes.NotesActivity
import com.example.notesapp.util.RequestConstants
import com.example.notesapp.util.StringUtils
import kotlinx.android.synthetic.main.activity_noteslist.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class NotesListActivity : AppCompatActivity(), OnNoteClickListener, KodeinAware {

    override val kodein by kodein()
    private val factory: NotesListViewModelFactory by instance()

    lateinit var viewModel: NotesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noteslist)

        viewModel = ViewModelProviders.of(this, factory).get(NotesListViewModel::class.java)

        val adapter = NotesItemAdapter(listOf(), viewModel, this)

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

    override fun OnNoteClick(item: NotesItem) {
        var intent = Intent(this, NotesActivity::class.java)
        intent.putExtra("title", item.title)
        intent.putExtra("note", item.note)
        intent.putExtra("id", item.id)
        startActivityForResult(intent, RequestConstants().EDIT_NOTE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RequestConstants().EDIT_NOTE_REQUEST && resultCode==RequestConstants().RESULT_OK) {
            var title = data?.getStringExtra(RequestConstants().EDIT_TITLE)
            var note = data?.getStringExtra(RequestConstants().EDIT_NOTE)
            var id = data?.getIntExtra(RequestConstants().EDIT_ID, -1)
            var time = StringUtils().getDateString()
            var item = NotesItem(title!!, note!!, time)
            item.id = id!!
            viewModel.upsert(item)
        }
    }
}