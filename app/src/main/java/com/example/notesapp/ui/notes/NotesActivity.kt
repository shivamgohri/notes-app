package com.example.notesapp.ui.notes

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.room.util.StringUtil
import com.example.notesapp.R
import com.example.notesapp.data.db.entities.NotesItem
import com.example.notesapp.ui.noteslist.NotesListActivity
import com.example.notesapp.ui.noteslist.NotesListViewModel
import com.example.notesapp.ui.noteslist.NotesListViewModelFactory
import com.example.notesapp.util.RequestConstants
import com.example.notesapp.util.StringUtils
import kotlinx.android.synthetic.main.activity_notes.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NotesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val id = intent.getIntExtra("id", -1)
        var title = intent.getStringExtra("title")
        var note = intent.getStringExtra("note")

        etTitleEdit.setText(title)
        etNoteEdit.setText(note)

        tvSave.setOnClickListener {

            title = etTitleEdit.text.toString()
            note = etNoteEdit.text.toString()

            if(title.isNullOrEmpty() || note.isNullOrEmpty()){
                Toast.makeText(this, "Please write all information!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var intent = Intent()
            intent.putExtra(RequestConstants().EDIT_TITLE, title)
            intent.putExtra(RequestConstants().EDIT_NOTE, note)
            intent.putExtra(RequestConstants().EDIT_ID, id)
            setResult(RequestConstants().RESULT_OK, intent)
            finish()
        }
    }
}