package com.example.domain

import com.example.domain.repository.ProgramRepository
import com.example.domain.usecase.GetProgramUseCaseImpl
import com.example.domain.utils.None
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.createTestCoroutineScope
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class GetProgramUseCaseTest {

    private lateinit var getProgramUseCaseImpl: GetProgramUseCaseImpl

    private val dispatcher = TestCoroutineScheduler()
    private val testScope = createTestCoroutineScope(dispatcher)

    @Mock
    lateinit var programRepository: ProgramRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProgramUseCaseImpl = GetProgramUseCaseImpl(programRepository)
    }

    @Test
    fun `getProgramUseCaseImpl will call programRepository success`(): Unit {
        testScope.launch {
            val params = None
            val response = Unit

            Mockito.`when`(programRepository.getPrograms()).thenReturn(response)

            getProgramUseCaseImpl.execute(params)

            Mockito.verify(programRepository, times(1)).getPrograms()
        }
    }

    @Test(expected = Exception::class)
    fun `getProgramUseCaseImpl will not call programRepository success`(): Unit = runBlocking {
        val params = None
        val response = Unit

        Mockito.`when`(programRepository.getPrograms()).thenThrow(Exception())

        getProgramUseCaseImpl.execute(params)

        Mockito.verify(programRepository, times(0)).getPrograms()
    }
}