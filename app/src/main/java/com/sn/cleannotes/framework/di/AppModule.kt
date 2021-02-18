package com.sn.cleannotes.framework.di

import android.content.Context
import androidx.room.Room
import com.sn.cleannotes.framework.IInteractions
import com.sn.cleannotes.framework.InteractionsImpl
import com.sn.cleannotes.framework.NoteViewModel
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
    fun provideNoteViewModel(interactionsImpl: InteractionsImpl) =
        NoteViewModel(interactionsImpl)

/*    @Provides
    fun provideAddNoteInteractor(noteRepository: NoteRepository) =
        AddNote(noteRepository)

    @Provides
    fun provideGetAllNotesInteractor(noteRepository: NoteRepository) =
        GetAllNotes(noteRepository)

    @Provides
    fun provideGetNoteInteractor(noteRepository: NoteRepository) =
        GetNote(noteRepository)

    @Provides
    fun provideRemoveNoteInteractor(noteRepository: NoteRepository) =
        RemoveNote(noteRepository)*/

    @Provides
    fun provideInteractions(noteRepository: NoteRepository): IInteractions {
        return InteractionsImpl(noteRepository)
    }
}

