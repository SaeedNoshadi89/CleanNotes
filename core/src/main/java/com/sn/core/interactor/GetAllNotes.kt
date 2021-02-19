package com.sn.core.interactor

import com.sn.core.data.Note
import com.sn.core.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotes constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(): Flow<MutableList<Note>> =
        noteRepository.getAllNotes()
}