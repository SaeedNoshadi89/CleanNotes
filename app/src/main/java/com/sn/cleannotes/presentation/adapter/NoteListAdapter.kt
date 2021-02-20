package com.sn.cleannotes.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sn.cleannotes.R
import com.sn.cleannotes.databinding.ItemNoteBinding
import com.sn.core.data.Note
import com.sn.core.util.Extension.dateFormat

class NoteListAdapter constructor(
    private val onClick: (note: Note, toBeDelete: Boolean) -> Unit
) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note) =
            oldItem.creationTime == newItem.creationTime

        override fun areContentsTheSame(oldItem: Note, newItem: Note) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        parent(parent)

    val differ = AsyncListDiffer(this, differCallback)
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(differ.currentList[position], onClick)
    }

    override fun getItemCount(): Int =
        differ.currentList.size

    private fun parent(parent: ViewGroup): NoteViewHolder =
        NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    inner class NoteViewHolder(private val itemNoteBinding: ItemNoteBinding) :
        RecyclerView.ViewHolder(itemNoteBinding.root) {
        fun bind(note: Note, onClick: (note: Note, toBeDelete: Boolean) -> Unit) {
            itemNoteBinding.apply {
                tvTitle.text = note.title
                tvContent.text = note.content
                tvDate.text = note.updateTime.dateFormat()

                noteLayout.setOnClickListener {
                    onClick(note, false)
                }
                imgDelete.setOnClickListener {
                    onClick(note, true)
                }
            }
        }
    }
}