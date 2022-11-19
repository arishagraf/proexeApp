package co.proexe.di

import com.example.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindGetProgramUseCase(getProgramUseCaseImpl: GetProgramUseCaseImpl): GetProgramUseCase

    @Binds
    abstract fun bindShowProgramUseCase(showProgramUseCaseImpl: ShowProgramUseCaseImpl): ShowProgramUseCase

    @Binds
    abstract fun bindTabsUseCase(getTabsUseCaseImpl: GetTabsUseCaseImpl): GetTabsUseCase
}