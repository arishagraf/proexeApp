package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.database.model.ProgramEntity

@Database(
    entities = [ProgramEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TVDatabase : RoomDatabase() {

    abstract fun getTVDAO(): TVDAO

    companion object {
        private var DB_INSTANCE: TVDatabase? = null
        private const val DATABASE_NAME = "TV_DB"

        fun getTVDatabaseInstance(context: Context): TVDatabase {
            return DB_INSTANCE ?: Room
                .databaseBuilder(
                    context.applicationContext,
                    TVDatabase::class.java,
                    DATABASE_NAME
                )
                .build()
                .also { DB_INSTANCE = it }
        }
    }
}