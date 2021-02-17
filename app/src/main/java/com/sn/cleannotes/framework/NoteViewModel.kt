package com.sn.cleannotes.framework

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sn.core.repository.NoteRepository

class NoteViewModel @ViewModelInject constructor(
    private val noteRepository: NoteRepository,
    private val intractors: Interactors
) :
    ViewModel() {

}