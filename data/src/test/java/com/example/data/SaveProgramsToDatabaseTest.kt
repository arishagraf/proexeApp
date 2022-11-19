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
class SaveProgramsToDatabaseTest {

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineScheduler()
    private val testScope = createTestCoroutineScope(dispatcher)

    lateinit var programRepository: ProgramRepository

    @Mock
    lateinit var programService: ProgramService

    @Mock
    lateinit var tvdao: TVDAO

    private val fakeProgramEntity = ProgramEntity(
        1,
        "",
        "",
        "",
        "",
        false,
        "",
        "",
        100
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        programRepository = ProgramRepositoryImpl(programService, tvdao)
    }

    @Test
    fun `getProgramItems saved to database success`() {
        testScope.launch {
            Mockito.`when`(tvdao.insertProgramEntity(fakeProgramEntity)).thenThrow(Exception())

            val listEntity = listOf<ProgramEntity>()
            listEntity.forEach {
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
                Mockito.verify(tvdao, Mockito.only()).insertProgramEntity(programEntity)
            }
        }
    }

    @Test
    fun `getProgramItems from database not success`() {
        testScope.launch {
            Mockito.`when`(tvdao.insertProgramEntity(fakeProgramEntity)).thenThrow(Exception())

            val actualResponse = programRepository.showPrograms()

            Assert.assertNotSame(fakeProgramEntity, actualResponse)
            org.junit.Assert.assertEquals(Exception(), programRepository.getPrograms())
            Mockito.verify(tvdao, Mockito.only()).insertProgramEntity(fakeProgramEntity)
        }
    }
}