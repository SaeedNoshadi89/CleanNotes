package com.sn.cleannotes.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
 @PrimaryKey(autoGenerate = true)
 val id: Long = 0L,
 val title: String = "",
 val content: String = "",
 @ColumnInfo(name = "creation_date")
 val creationTime: Long = 0L,
 @ColumnInfo(name = "update_time")
 val updateTime: Long = 0L,
)