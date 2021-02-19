package com.sn.cleannotes.framework.util

import javax.inject.Inject

data class ItemClickCallback @Inject constructor(
    val onClick: (id: Long) -> Unit
)