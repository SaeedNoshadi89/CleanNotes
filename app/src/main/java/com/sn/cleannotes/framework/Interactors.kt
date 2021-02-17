package com.sn.cleannotes.framework

import com.sn.core.interactor.AddNote
import com.sn.core.interactor.GetAllNotes
import com.sn.core.interactor.GetNote
import com.sn.core.interactor.RemoveNote
import javax.inject.Inject

abstract class Interactors @Inject constructor(
    private val addNote: AddNote,
    private val getAllNotes: GetAllNotes,
    private val getNote: GetNote,
    private val removeNote: RemoveNote
)