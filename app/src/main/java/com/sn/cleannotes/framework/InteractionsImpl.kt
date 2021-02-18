package com.sn.cleannotes.framework

import com.sn.core.data.Note
import com.sn.core.interactor.AddNote
import com.sn.core.interactor.GetAllNotes
import com.sn.core.interactor.GetNote
import com.sn.core.interactor.RemoveNote
import com.sn.core.repository.NoteRepository
import javax.inject.Inject

class InteractionsImpl @Inject constructor(private val noteRepository: NoteRepository): IInteractions{
    override fun addNote(note: Note): AddNote = AddNote(noteRepository)

    override fun getAllNotes(): GetAllNotes = GetAllNotes(noteRepository)

    override fun getNote(id: Long): GetNote = GetNote(noteRepository)

    override fun removeNote(note: Note): RemoveNote = RemoveNote(noteRepository)
}