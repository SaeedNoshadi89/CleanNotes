package com.sn.core.data

import kotlinx.coroutines.flow.Flow

interface Mapper<E, M> {
    fun fromNote(m: M): Flow<E>
    fun toNote(e: E): Flow<M>
}