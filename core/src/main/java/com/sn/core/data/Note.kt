package com.sn.core.data

data class Note(
    val id: Long = 0,
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
    var wordCount: Int = 0
)
