package com.sn.cleannotes.framework.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sn.cleannotes.framework.repository.NoteInteractions
import com.sn.core.data.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AddNoteViewModel @ViewModelInject constructor(
    private val noteInteractions: NoteInteractions
) : ViewModel() {

    private val currentNote =
        MutableStateFlow(Note(title = "", content = "", creationTime = 0L, updateTime = 0L))
    private val deletedNote = MutableStateFlow(false)


    fun saveNote(note: Note) = flow {
        noteInteractions.addNote(note)
        emit(true)
    }.flowOn(Dispatchers.IO)

    fun fetchCurrentNote(id: Long) {
        viewModelScope.launch {
            noteInteractions.getNote.invoke(id).collect {
                currentNote.value = it
            }
        }
    }

    val getCurrentNote: StateFlow<Note> = currentNote


    fun fetchDeleteNote(id: Long) {
        viewModelScope.launch {
            noteInteractions.apply {
                getNote.invoke(id).collect {
                    removeNote(it)
                    deletedNote.value = true
                }
            }

        }
    }

    val getDeletedNote: StateFlow<Boolean> = deletedNote
}