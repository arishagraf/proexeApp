package co.proexe.di

import com.example.data.repository.ProgramRepositoryImpl
import com.example.domain.repository.ProgramRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindProgramRepository(programRepositoryImpl: ProgramRepositoryImpl): ProgramRepository
}
