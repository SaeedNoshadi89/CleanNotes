package com.sn.cleannotes.framework.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNoteEntity(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNoteEntity(id: Long): Flow<NoteEntity>

    @Query("SELECT * FROM note")
    fun getAllNoteEntities(): Flow<MutableList<NoteEntity>>

    @Delete
    suspend fun deleteNoteEntity(noteEntity: NoteEntity)
}