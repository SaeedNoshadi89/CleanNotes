package com.sn.cleannotes.framework.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sn.cleannotes.framework.repository.NoteInteractions
import com.sn.core.data.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel @ViewModelInject constructor(
    private val noteInteractions: NoteInteractions
) : ViewModel() {
    private val saved = MutableStateFlow(false)

    fun saveNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            noteInteractions.addNote(note)
            saved.value = true
        }
    }

    fun getSavedState(): StateFlow<Boolean> = saved

}