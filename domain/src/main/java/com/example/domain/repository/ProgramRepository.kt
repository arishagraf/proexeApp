package com.example.domain.repository

import com.example.domain.model.ProgramModel

interface ProgramRepository{

    suspend fun getPrograms(): List<ProgramModel>
}