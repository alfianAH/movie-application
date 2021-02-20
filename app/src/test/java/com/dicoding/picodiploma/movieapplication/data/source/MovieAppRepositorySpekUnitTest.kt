package com.dicoding.picodiploma.movieapplication.data.source

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.picodiploma.movieapplication.data.source.local.LocalDataSource
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.movieapplication.utils.*
import com.dicoding.picodiploma.movieapplication.valueobject.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.spekframework.spek2.Spek

@RunWith(JUnitPlatform::class)
class MovieAppRepositorySpekUnitTest : Spek({
    val remote = mock(RemoteDataSource::class.java)
    val localDataSource = mock(LocalDataSource::class.java)
    val appExecutors = mock(AppExecutors::class.java)

    val movieAppRepository = FakeMovieAppRepository(remote, localDataSource, appExecutors)

    val movieResponses = DataDummy.generateRemoteDummyMovies()
    val movieId = movieResponses[0].id
    val movieGenresResponse = DataDummy.generateRemoteDummyMovieGenres()
    val tvSeriesResponses = DataDummy.generateRemoteDummyTVSeries()
    val tvSeriesId = tvSeriesResponses[0].id
    val tvSeriesGenresResponse = DataDummy.generateRemoteDummyTVSeriesGenres()

    group("Movie App Repository Test"){
        beforeEachTest {
            // Instant Task Executor Rule substitute in spek unit testing
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

        test("Get Movie"){
            val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>

            Mockito.`when`(localDataSource.getMovies(SortUtils.NAME)).thenReturn(dataSourceFactory)
            movieAppRepository.getMovies(SortUtils.NAME)

            val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))

            verify(localDataSource).getMovies(SortUtils.NAME)
            assertNotNull(movieEntities.data)
            assertEquals(movieResponses.size, movieEntities.data?.size)
        }

        test("Get TV Series"){
            val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVSeriesEntity>
            Mockito.`when`(localDataSource.getTVSeries(SortUtils.NAME)).thenReturn(dataSourceFactory)
            movieAppRepository.getTVSeries(SortUtils.NAME)

            val tvSeriesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTVSeries()))

            verify(localDataSource).getTVSeries(SortUtils.NAME)
            assertNotNull(tvSeriesEntities.data)
            assertEquals(tvSeriesResponses.size, tvSeriesEntities.data?.size)
        }

        test("Get Detail Movie"){
            val dummyMovie = MutableLiveData<MovieDetails>()
            dummyMovie.value = DataDummy.generateDummyDetailMovie(DataDummy.generateDummyMovies()[0], false)

            Mockito.`when`(localDataSource.getDetailMovieById(movieId)).thenReturn(dummyMovie)

            val movieEntity = LiveDataTestUtil.getValue(movieAppRepository.getDetailMovie(movieId)).data

            verify(localDataSource).getDetailMovieById(movieId)
            assertNotNull(movieEntity)
            assertNotNull(movieEntity?.movieEntity?.title)
            assertEquals(movieResponses[0].title, movieEntity?.movieEntity?.title)
            assertEquals(movieResponses[0].overview, movieEntity?.movieEntity?.overview)
            assertEquals(movieResponses[0].releaseDate, movieEntity?.movieEntity?.releaseDate)
            assertEquals(movieResponses[0].id, movieEntity?.movieEntity?.id)
            assertEquals(movieResponses[0].posterPath, movieEntity?.movieEntity?.posterPath)
            assertEquals(movieResponses[0].voteAverage, movieEntity?.movieEntity?.voteAverage)
        }

        test("Get Detail TV Series"){
            val dummyTVSeries = MutableLiveData<TVSeriesDetails>()
            dummyTVSeries.value = DataDummy.generateDummyDetailTVSeries(DataDummy.generateDummyTVSeries()[0], false)

            Mockito.`when`(localDataSource.getDetailTVSeriesById(tvSeriesId)).thenReturn(dummyTVSeries)

            val tvSeriesEntity = LiveDataTestUtil.getValue(movieAppRepository.getDetailTVSeries(tvSeriesId)).data

            verify(localDataSource).getDetailTVSeriesById(tvSeriesId)
            assertNotNull(tvSeriesEntity)
            assertNotNull(tvSeriesEntity?.tvSeriesEntity?.title)
            assertEquals(tvSeriesResponses[0].name, tvSeriesEntity?.tvSeriesEntity?.title)
            assertEquals(tvSeriesResponses[0].overview, tvSeriesEntity?.tvSeriesEntity?.overview)
            assertEquals(tvSeriesResponses[0].firstAirDate, tvSeriesEntity?.tvSeriesEntity?.releaseDate)
            assertEquals(tvSeriesResponses[0].id, tvSeriesEntity?.tvSeriesEntity?.id)
            assertEquals(tvSeriesResponses[0].posterPath, tvSeriesEntity?.tvSeriesEntity?.posterPath)
            assertEquals(tvSeriesResponses[0].voteAverage, tvSeriesEntity?.tvSeriesEntity?.voteAverage)
        }

        test("Get Movie Genres"){
            val dummyGenres = MutableLiveData<List<MovieGenreEntity>>()
            dummyGenres.value = DataDummy.generateDummyMovieGenres(movieId)

            Mockito.`when`(localDataSource.getMovieGenresById(movieId)).thenReturn(dummyGenres)

            val genreEntities = LiveDataTestUtil.getValue(movieAppRepository.getMovieGenres(movieId)).data

            verify(localDataSource).getMovieGenresById(movieId)
            assertNotNull(genreEntities)
            assertEquals(movieGenresResponse.size, genreEntities?.size)
        }

        test("Get TV Series Genres"){
            val dummyGenres = MutableLiveData<List<TVSeriesGenreEntity>>()
            dummyGenres.value = DataDummy.generateDummyTVSeriesGenres(tvSeriesId)

            Mockito.`when`(localDataSource.getTVSeriesGenresById(tvSeriesId)).thenReturn(dummyGenres)

            val genreEntities = LiveDataTestUtil.getValue(movieAppRepository.getTVSeriesGenres(tvSeriesId)).data

            verify(localDataSource).getTVSeriesGenresById(tvSeriesId)
            assertNotNull(genreEntities)
            assertEquals(tvSeriesGenresResponse.size, genreEntities?.size)
        }

        test("Get Favorite Movies"){
            val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>

            Mockito.`when`(localDataSource.getFavoriteMovies(SortUtils.NAME)).thenReturn(dataSourceFactory)
            movieAppRepository.getFavoriteMovies(SortUtils.NAME)

            val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))

            verify(localDataSource).getFavoriteMovies(SortUtils.NAME)
            assertNotNull(movieEntities.data)
            assertEquals(movieResponses.size, movieEntities.data?.size)
        }

        test("Get Favorite TV Series"){
            val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVSeriesEntity>

            Mockito.`when`(localDataSource.getFavoriteTVSeries(SortUtils.NAME)).thenReturn(dataSourceFactory)
            movieAppRepository.getFavoriteTVSeries(SortUtils.NAME)

            val tvSeriesEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTVSeries()))

            verify(localDataSource).getFavoriteTVSeries(SortUtils.NAME)
            assertNotNull(tvSeriesEntity.data)
            assertEquals(movieResponses.size, tvSeriesEntity.data?.size)
        }
    }
})