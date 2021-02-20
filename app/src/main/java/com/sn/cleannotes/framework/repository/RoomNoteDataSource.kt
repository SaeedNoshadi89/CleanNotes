package com.sn.cleannotes.framework.repository

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
) : NoteDataSource, Mapper<NoteEntity, Note, MutableList<NoteEntity>, MutableList<Note>> {
    override suspend fun add(note: Note) = run {
        fromNoteFlow(note).collect {
            noteDao.addNoteEntity(it)
        }
    }

    override fun get(id: Long): Flow<Note> = flow {
        noteDao.getNoteEntity(id).collect { noteEntity ->
            toNoteFlow(noteEntity).collect { note ->
                emit(note)
            }
        }
    }

    override fun getAll(): Flow<MutableList<Note>> = flow {
        noteDao.getAllNoteEntities().collect { noteEntityList ->
            toNoteListFlow(noteEntityList).collect { noteList ->
                emit(noteList)
            }
        }
    }

    override suspend fun remove(note: Note) = run {
        fromNoteFlow(note).collect {
            noteDao.deleteNoteEntity(it)
        }
    }

    override fun fromNoteFlow(m: Note?): Flow<NoteEntity> = flow {
        m?.let {
            emit(
                NoteEntity(
                    id = it.id,
                    title = it.title,
                    content = it.content,
                    creationTime = it.creationTime,
                    updateTime = it.updateTime
                )
            )
        }
    }

    override fun toNoteFlow(e: NoteEntity?): Flow<Note> = flow {
        e?.let {
            emit(
                Note(
                    id = it.id,
                    title = it.title,
                    content = it.content,
                    creationTime = it.creationTime,
                    updateTime = it.updateTime
                )
            )
        }
    }

    override fun fromNoteListFlow(tm: MutableList<Note>?): Flow<MutableList<NoteEntity>> = flow {
        tm?.let {
            emit(
                tm.map {
                    NoteEntity(
                        id = it.id,
                        title = it.title,
                        content = it.content,
                        creationTime = it.creationTime,
                        updateTime = it.updateTime
                    )
                }.toMutableList()
            )
        }
    }

    override fun toNoteListFlow(te: MutableList<NoteEntity>?): Flow<MutableList<Note>> = flow {
        te?.let {
            emit(
                te.map {
                    Note(
                        id = it.id,
                        title = it.title,
                        content = it.content,
                        creationTime = it.creationTime,
                        updateTime = it.updateTime
                    )
                }.toMutableList()
            )
        }
    }
}