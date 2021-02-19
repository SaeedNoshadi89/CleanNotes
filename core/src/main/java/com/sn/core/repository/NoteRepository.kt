package com.sn.core.repository

import com.sn.core.data.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository constructor(private val dataSource: NoteDataSource) {
    suspend fun addNote(note: Note) = dataSource.add(note)

    fun getNote(id: Long): Flow<Note> =
        dataSource.get(id)

    fun getAllNotes(): Flow<MutableList<Note>> =
        dataSource.getAll()

    suspend fun removeNote(note: Note) = dataSource.remove(note)
}