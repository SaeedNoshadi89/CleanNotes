package com.sn.cleannotes.framework.repository

import com.sn.core.interactor.*

class NoteInteractions constructor(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote,
    val getWordCount: GetWordCount
)