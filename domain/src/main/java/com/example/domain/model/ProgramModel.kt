package com.example.domain.model

data class ProgramModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val type: String,
    val category: String,
    val isFavourite: Boolean,
    val startTimeDateRaw: String,
    val endTimeDateRaw: String,
    val progressPercent: Int
)