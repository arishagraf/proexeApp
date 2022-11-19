package com.example.domain.model

import java.util.*

data class ProgramModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val type: String,
    val category: TvProgrammeCategory,
    val isFavourite: Boolean,
    val startTime: Date,
    val endTime: Date,
    val progressPercent: Int
)