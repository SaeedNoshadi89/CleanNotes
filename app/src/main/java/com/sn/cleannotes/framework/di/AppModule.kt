package com.sn.cleannotes.framework.di

import android.content.Context
import androidx.room.Room
import com.sn.cleannotes.framework.db.Database
import com.sn.cleannotes.framework.db.NoteDao
import com.sn.cleannotes.framework.repository.NoteInteractions
import com.sn.cleannotes.framework.repository.RoomNoteDataSource
import com.sn.cleannotes.framework.viewmodel.NoteListViewModel
import com.sn.cleannotes.framework.viewmodel.AddNoteViewModel
import com.sn.core.interactor.*
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
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideNoteDao(db: Database) = db.getNoteDao()

    @Provides
    fun provideRoomNoteDataSource(noteDao: NoteDao) =
        RoomNoteDataSource(noteDao)

    @Provides
    fun provideNoteRepository(roomNoteDataSource: RoomNoteDataSource) =
        NoteRepository(roomNoteDataSource)

    @Provides
    fun provideNoteViewModel(noteInteractions: NoteInteractions) =
        AddNoteViewModel(noteInteractions)

    @Provides
    fun provideNoteListViewModel(noteInteractions: NoteInteractions) =
        NoteListViewModel(noteInteractions)

    @Provides
    fun provideNoteInteractions(repository: NoteRepository) = NoteInteractions(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount()
    )
}

