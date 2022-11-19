package com.example.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.data.database.TVDAO
import com.example.data.database.model.ProgramEntity
import com.example.data.repository.ProgramRepositoryImpl
import com.example.data.service.ProgramService
import com.example.domain.repository.ProgramRepository
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.createTestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ShowProgramsFromDatabaseTest {

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineScheduler()
    private val testScope = createTestCoroutineScope(dispatcher)

    lateinit var programRepository: ProgramRepository

    @Mock
    lateinit var programService: ProgramService

    @Mock
    lateinit var tvdao: TVDAO

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        programRepository = ProgramRepositoryImpl(programService, tvdao)
    }

    @Test
    fun `getProgramItems from database success`() {
        val response = listOf<ProgramEntity>()

        testScope.launch {
            Mockito.`when`(tvdao.getProgramEntity()).thenReturn(response)

            programRepository.showPrograms()

            Mockito.verify(tvdao, Mockito.times(1)).getProgramEntity()
        }
    }

    @Test
    fun `getProgramItems from database not success`() {
        val expectedResponse = listOf<ProgramEntity>()

        testScope.launch {
            Mockito.`when`(tvdao.getProgramEntity()).thenThrow(Exception())

            val actualResponse = programRepository.showPrograms()

            Assert.assertNotSame(expectedResponse, actualResponse)
            org.junit.Assert.assertEquals(Exception(), programRepository.showPrograms())
            Mockito.verify(tvdao, Mockito.only()).getProgramEntity()
        }
    }
}