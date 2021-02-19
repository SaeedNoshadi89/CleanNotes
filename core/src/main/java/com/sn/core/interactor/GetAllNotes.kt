package com.sn.core.interactor

import com.sn.core.repository.NoteRepository

class GetAllNotes constructor(private val noteRepository: NoteRepository) {
    operator fun invoke() = noteRepository.getAllNotes()
}