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
    private val deletedNote = MutableStateFlow(false)

    fun getAllNotes(){
        viewModelScope.launch(Dispatchers.IO){
            noteInteractions.apply {
                getAllNotes.invoke().collect {
                    it.forEach {note ->
                        note.wordCount = getWordCount(note)
                    }
                    notes.value = it
                }
            }
        }
    }

    val getAllNotesState: StateFlow<MutableList<Note>> = notes

    fun fetchDeleteNote(note: Note){
        viewModelScope.launch {
            noteInteractions.removeNote(note)
            deletedNote.value = true
        }
    }
    val getDeletedNote: StateFlow<Boolean> = deletedNote

}