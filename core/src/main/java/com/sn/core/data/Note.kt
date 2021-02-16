package com.sn.core.data

data class Note(
    private val id: Long = 0,
    private val title: String,
    private val content: String,
    private val creationTime: Long,
    private val updateTime: Long,
)
