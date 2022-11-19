package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.model.ProgramEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TVDAO {

    @Insert
    fun insertProgramEntity(programEntity: ProgramEntity)

    @Query("SELECT * FROM programItems")
    fun getProgramEntity(): List<ProgramEntity>

    @Query("SELECT EXISTS(SELECT * FROM programItems WHERE id = :id)")
    fun doesProgramExist(id: Int): Boolean?
}
