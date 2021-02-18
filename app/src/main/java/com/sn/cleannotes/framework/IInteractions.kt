package com.sn.cleannotes.framework

import com.sn.core.data.Note
import com.sn.core.interactor.AddNote
import com.sn.core.interactor.GetAllNotes
import com.sn.core.interactor.GetNote
import com.sn.core.interactor.RemoveNote

interface IInteractions {
    fun addNote(note: Note): AddNote
    fun getAllNotes(): GetAllNotes
    fun getNote(id: Long): GetNote
    fun removeNote(note: Note): RemoveNote
}