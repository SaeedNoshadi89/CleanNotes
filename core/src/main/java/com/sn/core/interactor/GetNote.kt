package com.sn.core.interactor

import com.sn.core.repository.NoteRepository

class GetNote constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNote(id)
}