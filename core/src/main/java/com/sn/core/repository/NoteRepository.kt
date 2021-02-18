package com.sn.core.repository

import com.sn.core.data.Note

class NoteRepository constructor(private val dataSource: NoteDataSource) {
    suspend fun addNote(note: Note) = dataSource.add(note)
    fun getNote(id: Long) = dataSource.get(id)
    fun getAllNotes() = dataSource.getAll()
    suspend fun removeNote(note: Note) = dataSource.remove(note)
}