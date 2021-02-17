package com.sn.cleannotes.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = REPLACE)
    suspend fun addNoteEntity(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteEntity(id: Long): Flow<NoteEntity>

    @Query("SELECT 8 FROM note")
    suspend fun getAllNoteEntities(): Flow<List<NoteEntity>>

    @Delete
    suspend fun deleteNoteEntity(noteEntity: NoteEntity)
}