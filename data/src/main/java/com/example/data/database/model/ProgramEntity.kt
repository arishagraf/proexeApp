package com.example.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.ProgramModel

@Entity(tableName = "programItems")
data class ProgramEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "isFavourite")
    val isFavourite: Boolean,
    @ColumnInfo(name = "startTimeDateRaw")
    val startTimeDateRaw: String,
    @ColumnInfo(name = "endTimeDateRaw")
    val endTimeDateRaw: String,
    @ColumnInfo(name = "progressPercent")
    val progressPercent: Int
) {
    companion object {
        fun from(programModel: ProgramModel): ProgramEntity {
            return ProgramEntity(
                programModel.id,
                programModel.title,
                programModel.imageUrl,
                programModel.type,
                programModel.category,
                programModel.isFavourite,
                programModel.startTimeDateRaw,
                programModel.endTimeDateRaw,
                programModel.progressPercent
            )
        }
    }

    fun toProgramModel(): ProgramModel {
        return ProgramModel(
            id,
            title,
            imageUrl,
            type,
            category,
            isFavourite,
            startTimeDateRaw,
            endTimeDateRaw,
            progressPercent
        )
    }
}