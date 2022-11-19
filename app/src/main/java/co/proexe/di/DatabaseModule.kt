package co.proexe.di

import android.app.Application
import com.example.data.database.TVDAO
import com.example.data.database.TVDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideTVDatabase(context: Application): TVDatabase {
        return TVDatabase.getTVDatabaseInstance(context)
    }

    @Provides
    @Singleton
    fun provideTVDAO(tvDatabase: TVDatabase): TVDAO {
        return tvDatabase.getTVDAO()
    }
}