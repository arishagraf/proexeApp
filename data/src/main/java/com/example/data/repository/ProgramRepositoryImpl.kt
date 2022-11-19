package com.example.data.repository

import com.example.data.database.TVDAO
import com.example.data.database.model.ProgramEntity
import com.example.data.service.ProgramService
import com.example.domain.model.ProgramModel
import com.example.domain.model.TvProgrammeCategory
import com.example.domain.repository.ProgramRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProgramRepositoryImpl @Inject constructor(
    private val programService: ProgramService,
    private val tvdao: TVDAO
) : ProgramRepository {

    override suspend fun getPrograms() {
        withContext(Dispatchers.IO) {
            val response = programService.getProgramItems()
            response.forEach {
                val programEntity = ProgramEntity(
                    it.id,
                    it.title,
                    it.imageUrl,
                    it.type,
                    it.category,
                    it.isFavourite,
                    it.startTimeDateRaw,
                    it.endTimeDateRaw,
                    it.progressPercent
                )
                val programItemExists = tvdao.doesProgramExist(programEntity.id)
                if (programItemExists == null || programItemExists == false) {
                    tvdao.insertProgramEntity(programEntity)
                }
            }
        }
    }

    override suspend fun showPrograms(): List<ProgramModel> {
        return withContext(Dispatchers.IO) {
            tvdao.getProgramEntity().map { programEntity ->
                programEntity.toProgramModel()
            }
        }
    }

    override suspend fun getTabs(): List<String> {
        return listOf<String>(
            TvProgrammeCategory.ALL.name,
            TvProgrammeCategory.KIDS.name,
            TvProgrammeCategory.EDUCATIONAL.name,
            TvProgrammeCategory.MOVIES_AND_SERIES.name,
            TvProgrammeCategory.INFO.name,
            TvProgrammeCategory.MUSIC.name,
            TvProgrammeCategory.GENERAL.name,
            TvProgrammeCategory.SPORT.name,
            TvProgrammeCategory.LIFESTYLE.name,
        )
    }
}