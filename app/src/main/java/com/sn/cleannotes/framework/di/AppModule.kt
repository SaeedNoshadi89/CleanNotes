package com.sn.cleannotes.framework.di

import android.content.Context
import androidx.room.Room
import com.sn.cleannotes.framework.RoomNoteDataSource
import com.sn.cleannotes.framework.db.Database
import com.sn.cleannotes.framework.db.NoteDao
import com.sn.core.repository.NoteRepository
import com.sn.core.util.Constant.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext, Database::class.java, DB_NAME)

    @Singleton
    @Provides
    fun provideNoteDao(db: Database) = db.getNoteDao()

    @Provides
    fun provideRoomNoteDataSource(@ApplicationContext appContext: Context, noteDao: NoteDao) =
        RoomNoteDataSource(appContext, noteDao)

    @Provides
    fun provideNoteRepository(roomNoteDataSource: RoomNoteDataSource) =
        NoteRepository(roomNoteDataSource)

/*    @Binds
    fun provideIntractors(
        addNote: AddNote,
        getAllNotes: GetAllNotes,
        getNote: GetNote,
        removeNote: RemoveNote
    ): Interactors{
        return Interactors(addNote, getAllNotes, getNote, removeNote)
    }*/

}