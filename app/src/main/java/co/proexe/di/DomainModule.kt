package co.proexe.di

import com.example.domain.usecase.GetProgramUseCase
import com.example.domain.usecase.GetProgramUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindProgramUseCase(getProgramUseCaseImpl: GetProgramUseCaseImpl): GetProgramUseCase
}