package com.sn.cleannotes.framework.repository

import com.sn.core.interactor.AddNote
import com.sn.core.interactor.GetAllNotes
import com.sn.core.interactor.GetNote
import com.sn.core.interactor.RemoveNote

class NoteInteractions constructor(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)