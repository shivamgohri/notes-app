package com.example.notesapp.ui.noteslist

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.notesapp.R
import com.example.notesapp.data.db.entities.NotesItem
import kotlinx.android.synthetic.main.dialog_add_notes_item.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AddNotesItemDialog(
    context: Context,
    var addNotesDialogListener: AddNotesDialogListener
): AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_notes_item)

        tvAdd.setOnClickListener {
            val title = etTitle.text.toString()
            val note = etNote.text.toString()

            if(title.isEmpty() || note.isEmpty()){
                Toast.makeText(context, "Please add all information!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var time: String
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
                time =  current.format(formatter)
            } else {
                var date = Date()
                val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
                time = formatter.format(date)
            }
            val item = NotesItem(title, note, time)
            addNotesDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tvCancel.setOnClickListener { dismiss() }
    }
}