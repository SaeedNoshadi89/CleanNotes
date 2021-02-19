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

class AddNoteViewModel @ViewModelInject constructor(
    private val noteInteractions: NoteInteractions
) : ViewModel() {
    private val saved = MutableStateFlow(false)
    private val currentNote = MutableStateFlow(Note(title = "", content = "", creationTime = 0L, updateTime = 0L))

    fun saveNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            noteInteractions.addNote(note)
            saved.value = true
        }
    }
    val getSavedState: StateFlow<Boolean> = saved

    fun fetchCurrentNote(id: Long){
        viewModelScope.launch {
            noteInteractions.getNote.invoke(id).collect {
                currentNote.value = it
            }
        }
    }

    val getCurrentNote: StateFlow<Note> = currentNote
}