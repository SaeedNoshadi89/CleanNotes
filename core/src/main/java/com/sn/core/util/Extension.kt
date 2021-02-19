package com.sn.core.util

import java.text.SimpleDateFormat
import java.util.*

object Extension {
    fun String.checkStringNotNullOrEmpty() = isEmpty()

    @Suppress("SimpleDateFormat")
    fun Long.dateFormat(): String? =
        SimpleDateFormat("MMM dd, HH:mm:ss").format(Date(this))
}
