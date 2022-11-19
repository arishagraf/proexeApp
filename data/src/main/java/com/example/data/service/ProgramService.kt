package com.example.data.service

import com.example.domain.model.ProgramModel
import retrofit2.http.GET

interface ProgramService {

    @GET("GG8C")
    suspend fun getProgramItems(): List<ProgramModel>
}