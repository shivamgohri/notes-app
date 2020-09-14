package com.example.notesapp.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.db.entities.NotesItem
import com.example.notesapp.ui.noteslist.NotesListViewModel
import kotlinx.android.synthetic.main.notes_item.view.*

class NotesItemAdapter(
    var items: List<NotesItem>,
    private val viewModel: NotesListViewModel
): RecyclerView.Adapter<NotesItemAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.notes_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currNotesItem = items[position]

        holder.itemView.tvTitle.text = currNotesItem.title
        holder.itemView.tvNote.text = currNotesItem.note
        holder.itemView.tvTime.text = currNotesItem.time

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(currNotesItem)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}