package com.example.domain

import com.example.domain.model.ProgramModel
import com.example.domain.repository.ProgramRepository
import com.example.domain.usecase.GetProgramUseCaseImpl
import com.example.domain.usecase.ShowProgramUseCase
import com.example.domain.usecase.ShowProgramUseCaseImpl
import com.example.domain.utils.None
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.createTestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ShowProgramUseCaseTest {

    private val dispatcher = TestCoroutineScheduler()
    private val testScope = createTestCoroutineScope(dispatcher)

    private lateinit var showProgramUseCaseImpl: ShowProgramUseCaseImpl

    @Mock
    lateinit var programRepository: ProgramRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        showProgramUseCaseImpl = ShowProgramUseCaseImpl(programRepository)
    }

    @Test
    fun `showProgramUseCaseImpl will call programRepository success`(): Unit = runTest {
        val params = None
        val response = listOf<ProgramModel>()

        testScope.launch {
            Mockito.`when`(programRepository.showPrograms()).thenReturn(response)

            showProgramUseCaseImpl.execute(params)

            Mockito.verify(programRepository, Mockito.only()).showPrograms()
        }
    }

    @Test
    fun `showProgramUseCaseImpl will not call programRepository success`(): Unit = runTest {
        val params = None

        testScope.launch {
            Mockito.`when`(programRepository.showPrograms()).thenThrow(Exception())

            showProgramUseCaseImpl.execute(params)

            Mockito.verify(programRepository, Mockito.never()).showPrograms()
        }
    }
}