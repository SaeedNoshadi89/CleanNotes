package com.sn.core.interactors

import com.sn.core.repository.NoteRepository

class GetAllNotes constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAllNotes()
}