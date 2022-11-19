package com.example.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.data.database.TVDAO
import com.example.data.database.model.ProgramEntity
import com.example.data.repository.ProgramRepositoryImpl
import com.example.data.service.ProgramService
import com.example.domain.model.ProgramModel
import com.example.domain.repository.ProgramRepository
import junit.framework.Assert.assertNotSame
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.createTestCoroutineScope
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetProgramsRequestTest {

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
    fun `getProgramItems request success`() {
        val response = listOf<ProgramModel>()

        testScope.launch {
            Mockito.`when`(programService.getProgramItems()).thenReturn(response)

            programRepository.getPrograms()

            Mockito.verify(programService, Mockito.only()).getProgramItems()
        }
    }

    @Test
    fun `getProgramItems saved to database success`() {
        val response = listOf<ProgramModel>()

        testScope.launch {
            Mockito.`when`(programService.getProgramItems()).thenReturn(response)

            programRepository.getPrograms()

            response.forEach {
                val programEntity = ProgramEntity(
                    it.id,
                    it.title,
                    it.imageUrl,
                    it.type,
                    it.category,
                    it.isFavourite,
                    it.startTimeDateRaw,
                    it.endTimeDateRaw,
                    it.progressPercent
                )
                verify(tvdao, only()).insertProgramEntity(programEntity)
            }
        }
    }

    @Test
    fun `getProgramItems request not success`() {
        val expectedResponse = listOf<ProgramModel>()

        testScope.launch {
            Mockito.`when`(programService.getProgramItems()).thenThrow(Exception())

            val actualResponse = programRepository.getPrograms()

            assertNotSame(expectedResponse, actualResponse)
            Assert.assertEquals(Exception(), programRepository.getPrograms())
            Mockito.verify(programService, Mockito.only()).getProgramItems()
        }
    }

    @Test
    fun `getProgramItems not saved in database`() {
        val expectedResponse = listOf<ProgramModel>()

        testScope.launch {
            Mockito.`when`(programService.getProgramItems()).thenThrow(Exception())

            programRepository.getPrograms()

            expectedResponse.forEach {
                val programEntity = ProgramEntity(
                    it.id,
                    it.title,
                    it.imageUrl,
                    it.type,
                    it.category,
                    it.isFavourite,
                    it.startTimeDateRaw,
                    it.endTimeDateRaw,
                    it.progressPercent
                )
                verify(tvdao, never()).insertProgramEntity(programEntity)
            }
        }
    }
}