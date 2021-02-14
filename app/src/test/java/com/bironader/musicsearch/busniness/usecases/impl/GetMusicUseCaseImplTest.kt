package com.bironader.musicsearch.busniness.usecases.impl

import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.bironader.musicsearch.busniness.repositories.abstraction.GetMusicRepository
import com.bironader.musicsearch.busniness.repositories.impl.GetMusicRepoImpl
import com.bironader.musicsearch.busniness.usecases.abstraction.GetMusicUseCase
import com.bironader.musicsearch.framework.datasource.remote.abstraction.SearchDataSource
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

@Suppress("UNREACHABLE_CODE")
@ExperimentalCoroutinesApi
class GetMusicUseCaseImplTest {

    private lateinit var getMusicListUseCase: GetMusicUseCase

    @Test
    fun `getMusic()_success_returnResponse`() {
        runBlockingTest {
            //Arrange
            val result = emptyList<MusicDomainModel>() // empty list
            getMusicListUseCase = GetMusicUseCaseImpl(object : GetMusicRepository {
                override suspend fun getMusic(query: String) = flowOf(result)

            })

            //Act
            var success: List<MusicDomainModel>? = null
            var throwable: Throwable? = null
            val flow = getMusicListUseCase.getMusics("query")
            flow.catch { throwable = it }
                .collect { success = it }


            // Assert
            Assert.assertNotNull(success)
            Assert.assertNull(throwable)
            Assert.assertEquals(success, result)
        }

    }


    @Test
    fun `getMusic()_failed_returnThrowable`() {
        runBlockingTest {
            //Arrange
            val result = Throwable("Http error")

            getMusicListUseCase = GetMusicUseCaseImpl(object : GetMusicRepository {
                override suspend fun getMusic(query: String) = flow {
                    throw result
                    emit(emptyList<MusicDomainModel>())
                }

            })

            //Act
            var success: List<MusicDomainModel>? = null
            var throwable: Throwable? = null
            val flow = getMusicListUseCase.getMusics("query")
            flow.catch { throwable = it }
                .collect { success = it }


            // Assert
            Assert.assertNotNull(throwable)
            Assert.assertNull(success)
            Assert.assertEquals(throwable?.message, result.message)
        }

    }
}