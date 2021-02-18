package com.sn.core.repository

import com.sn.core.data.Note
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {
    suspend fun add(note: Note)
    fun get(id: Long): Flow<Note>
    fun getAll(): Flow<List<Note>>
    suspend fun remove(note: Note)
}