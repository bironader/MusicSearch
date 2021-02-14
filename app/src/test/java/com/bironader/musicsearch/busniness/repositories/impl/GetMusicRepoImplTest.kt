package com.bironader.musicsearch.busniness.repositories.impl

import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.bironader.musicsearch.busniness.repositories.abstraction.GetMusicRepository
import com.bironader.musicsearch.framework.datasource.remote.abstraction.SearchDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

@Suppress("UNREACHABLE_CODE")
@ExperimentalCoroutinesApi
class GetMusicRepoImplTest {

    private lateinit var getMusicRepo: GetMusicRepository


    @Test
    fun `getMusic()_success_returnResponse`() {
        runBlockingTest {
            //Arrange
            val result = emptyList<MusicDomainModel>() // empty list
            getMusicRepo = GetMusicRepoImpl(object : SearchDataSource {
                override suspend fun searchForMusic(query: String) = flowOf(result)
            })

            //Act
            var success: List<MusicDomainModel>? = null
            var throwable: Throwable? = null
            val flow = getMusicRepo.getMusic("query")
            flow.catch { throwable = it }
                .collect { success = it }


            // Assert
            assertNotNull(success)
            assertNull(throwable)
            assertEquals(success, result)
        }

    }


    @Test
    fun `getMusic()_failed_returnThrowable`() {
        runBlockingTest {
            //Arrange
            val result = Throwable("Http error") // empty list
            getMusicRepo = GetMusicRepoImpl(object : SearchDataSource {
                override suspend fun searchForMusic(query: String) =  flow {
                    throw result
                    emit(emptyList<MusicDomainModel>())
                }

            })

            //Act
            var success: List<MusicDomainModel>? = null
            var throwable: Throwable? = null
            val flow = getMusicRepo.getMusic("query")
            flow.catch { throwable = it }
                .collect { success = it }


            // Assert
            assertNotNull(throwable)
            assertNull(success)
            assertEquals(throwable?.message, result.message)
        }

    }
}