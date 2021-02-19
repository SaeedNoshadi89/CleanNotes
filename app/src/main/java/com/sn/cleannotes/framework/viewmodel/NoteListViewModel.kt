package com.sn.cleannotes.framework.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sn.cleannotes.framework.repository.NoteInteractions
import com.sn.core.data.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NoteListViewModel @ViewModelInject constructor(
    private val noteInteractions: NoteInteractions
) : ViewModel() {
    private val notes = MutableStateFlow<MutableList<Note>>(mutableListOf())

    fun getAllNotes(){
        viewModelScope.launch(Dispatchers.IO){
            noteInteractions.getAllNotes.invoke().collect {
                notes.value = it
            }
        }
    }

    fun getAllNotesState(): StateFlow<MutableList<Note>> = notes

}