package com.sn.core.interactor

import com.sn.core.data.Note
import com.sn.core.repository.NoteRepository

class RemoveNote constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.removeNote(note)
}