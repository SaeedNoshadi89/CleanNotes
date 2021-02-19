package com.sn.core.data

import kotlinx.coroutines.flow.Flow

interface Mapper<E, M, TE : MutableList<E>, TM : MutableList<M>> {
    fun fromNoteFlow(m: M): Flow<E>
    fun toNoteFlow(e: E): Flow<M>
    fun fromNoteListFlow(tm: TM): Flow<TE>
    fun toNoteListFlow(te: TE): Flow<TM>
}