package com.sn.cleannotes.framework

import com.sn.cleannotes.framework.db.NoteDao
import com.sn.cleannotes.framework.db.NoteEntity
import com.sn.core.data.Mapper
import com.sn.core.data.Note
import com.sn.core.repository.NoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

// this class is Note repository
class RoomNoteDataSource @Inject constructor(
    private val noteDao: NoteDao
) : NoteDataSource, Mapper<NoteEntity, Note> {
    override suspend fun add(note: Note) = run {
        fromNote(note).collect {
            noteDao.addNoteEntity(it)
        }
    }

    override fun get(id: Long): Flow<Note> = flow {
        noteDao.getNoteEntity(id).collect { noteEntity ->
            toNote(noteEntity).collect { note ->
                emit(note)
            }
        }
    }

    override fun getAll(): Flow<List<Note>> = flow {
        noteDao.getAllNoteEntities().collect {
            it.map { noteEntity ->
                toNote(noteEntity)
            }
        }
    }

    override suspend fun remove(note: Note) = run {
        fromNote(note).collect {
            noteDao.deleteNoteEntity(it)
        }
    }

    override fun fromNote(m: Note): Flow<NoteEntity> = flow {
        emit(
            NoteEntity(
                id = m.id,
                title = m.title,
                content = m.content,
                creationTime = m.creationTime,
                updateTime = m.updateTime
            )
        )
    }

    override fun toNote(e: NoteEntity): Flow<Note> = flow {
        emit(
            Note(
                id = e.id,
                title = e.title,
                content = e.content,
                creationTime = e.creationTime,
                updateTime = e.updateTime
            )
        )
    }
}