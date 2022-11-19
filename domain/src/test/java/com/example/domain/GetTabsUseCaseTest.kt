package com.example.domain

import com.example.domain.repository.ProgramRepository
import com.example.domain.usecase.GetTabsUseCaseImpl
import com.example.domain.utils.None
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.BDDMockito.never
import org.mockito.BDDMockito.times
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetTabsUseCaseTest {

    private lateinit var getTabsUseCaseImpl: GetTabsUseCaseImpl

    @Mock
    lateinit var programRepository: ProgramRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getTabsUseCaseImpl = GetTabsUseCaseImpl(programRepository)
    }

    @Test
    fun `getTabsUseCaseImpl will call programRepository success`(): Unit = runBlocking {
        val params = None
        val response = listOf<String>()

        `when`(programRepository.getTabs()).thenReturn(response)

        getTabsUseCaseImpl.execute(params)

        Mockito.verify(programRepository, times(1)).getTabs()
    }

    @Test(expected = Exception::class)
    fun `getTabsUseCaseImpl will not call programRepository success`(): Unit = runBlocking {
        val params = None

        `when`(programRepository.getTabs()).thenThrow(Exception())

        getTabsUseCaseImpl.execute(params)

        Mockito.verify(programRepository, never()).getTabs()
    }
}