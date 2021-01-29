package com.dicoding.picodiploma.movieapplication.data.source

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.dicoding.picodiploma.movieapplication.BuildConfig
import com.dicoding.picodiploma.movieapplication.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.DetailMovieResponse
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.DetailTVSeriesResponse
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.MovieResultsItem
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.TVSeriesResultsItem
import com.dicoding.picodiploma.movieapplication.utils.APILoaderHelper
import com.dicoding.picodiploma.movieapplication.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.spekframework.spek2.Spek
import retrofit2.Call

@RunWith(JUnitPlatform::class)
class MovieAppRepositorySpekUnitTest : Spek({

    lateinit var detailMovieResponse: Call<DetailMovieResponse>
    lateinit var detailTVSeriesResponse: Call<DetailTVSeriesResponse>
    lateinit var movieEntities: List<MovieResultsItem>
    lateinit var tvSeriesEntities: List<TVSeriesResultsItem>
    var movieId: Int? = 0
    var tvSeriesId: Int? = 0

    val apiKey = BuildConfig.TMBDApiKey
    val movieResponse = APILoaderHelper().findMovies(apiKey)
    val tvSeriesResponse = APILoaderHelper().findTVSeries(apiKey)
    val remote = Mockito.mock(RemoteDataSource::class.java)
    val movieAppRepository = FakeMovieAppRepository(remote)

    group("Movie App Repository Test"){
        beforeEachTest {
            ArchTaskExecutor.getInstance().setDelegate(object: TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) {
                    runnable.run()
                }

                override fun postToMainThread(runnable: Runnable) {
                    runnable.run()
                }

                override fun isMainThread(): Boolean = true
            })
        }

        afterEachTest {
            ArchTaskExecutor.getInstance().setDelegate(null)
        }

        group("Get Movie"){
            beforeEachTest {
                doAnswer { invocation ->
                    (invocation.arguments[1] as RemoteDataSource.LoadMoviesCallback)
                            .onAllMoviesReceived(movieResponse)
                    null
                }.`when`(remote).getMovies(eq(apiKey), any())

                movieEntities = LiveDataTestUtil.getValue(movieAppRepository.getMovies(apiKey))

                movieId = movieEntities[0].id
                detailMovieResponse = APILoaderHelper().findDetailMovie(apiKey, movieId as Int)
            }

            test("Get movie"){
                verify(remote).getMovies(eq(apiKey), any())
                assertNotNull(movieEntities)
                assertEquals(20, movieEntities.size)
            }
        }

        group("Get TV Series"){
            beforeEachTest {
                doAnswer { invocation ->
                    (invocation.arguments[1] as RemoteDataSource.LoadTVSeriesCallback)
                            .onAllTVSeriesReceived(tvSeriesResponse)
                    null
                }.`when`(remote).getTVSeries(eq(apiKey), any())

                tvSeriesEntities = LiveDataTestUtil.getValue(movieAppRepository.getTVSeries(apiKey))

                tvSeriesId = tvSeriesEntities[0].id
                detailTVSeriesResponse = APILoaderHelper().findDetailTVSeries(apiKey, tvSeriesId as Int)
            }

            test("Get TV Series"){
                verify(remote).getTVSeries(eq(apiKey), any())
                assertNotNull(tvSeriesEntities)
                assertEquals(20, tvSeriesEntities.size)
            }
        }

        group("Get Detail Movie"){
            test("Get Detail Movie"){
                doAnswer { invocation ->
                    (invocation.arguments[2] as RemoteDataSource.LoadDetailMovieCallback)
                            .onDetailMovieReceived(detailMovieResponse)
                    null
                }.`when`(remote).getDetailMovie(eq(apiKey), eq(movieId as Int), any())

                val detailMovieEntity =
                        LiveDataTestUtil.getValue(movieAppRepository.getDetailMovie(apiKey, movieId as Int))

                verify(remote).getDetailMovie(eq(apiKey), eq(movieId as Int), any())
                assertNotNull(detailMovieEntity)
                assertEquals("Wonder Woman 1984", detailMovieEntity.title)
            }
        }

        group("Get Detail TV Series"){
            test("Get Detail TV Series"){
                doAnswer { invocation ->
                    (invocation.arguments[2] as RemoteDataSource.LoadDetailTVSeriesCallback)
                            .onDetailTVSeriesReceived(detailTVSeriesResponse)
                    null
                }.`when`(remote).getDetailTVSeries(eq(apiKey), eq(tvSeriesId as Int), any())

                val detailTVSeriesEntity =
                        LiveDataTestUtil.getValue(movieAppRepository.getDetailTVSeries(apiKey, tvSeriesId as Int))

                verify(remote).getDetailTVSeries(eq(apiKey), eq(tvSeriesId as Int), any())
                assertNotNull(detailTVSeriesEntity)
                assertEquals("I Am Not an Animal", detailTVSeriesEntity.name)
            }
        }
    }
})