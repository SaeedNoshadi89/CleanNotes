package com.sn.core.interactors

import com.sn.core.data.Note
import com.sn.core.repository.NoteRepository

class AddNote constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}