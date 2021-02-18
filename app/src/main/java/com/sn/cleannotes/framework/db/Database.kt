package com.sn.cleannotes.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}
