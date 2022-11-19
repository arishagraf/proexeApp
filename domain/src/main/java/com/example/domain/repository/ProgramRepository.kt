package com.example.domain.repository

import com.example.domain.model.ProgramModel

interface ProgramRepository{

    suspend fun getPrograms()

    suspend fun showPrograms(): List<ProgramModel>

    suspend fun getTabs(): List<String>
}