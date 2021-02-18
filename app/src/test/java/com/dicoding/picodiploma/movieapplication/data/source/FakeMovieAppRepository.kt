package com.dicoding.picodiploma.movieapplication.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.picodiploma.movieapplication.data.MovieAppDataSource
import com.dicoding.picodiploma.movieapplication.data.NetworkBoundResource
import com.dicoding.picodiploma.movieapplication.data.source.local.LocalDataSource
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.remote.ApiResponse
import com.dicoding.picodiploma.movieapplication.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.*
import com.dicoding.picodiploma.movieapplication.utils.AppExecutors
import com.dicoding.picodiploma.movieapplication.valueobject.Resource

class FakeMovieAppRepository constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
):
        MovieAppDataSource {

    override fun getMovies(sortBy: String): LiveData<Resource<PagedList<MovieEntity>>> {

        return object: NetworkBoundResource<PagedList<MovieEntity>, List<MovieResultsItem>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()

                return LivePagedListBuilder(localDataSource.getMovies(sortBy), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResultsItem>>> =
                    remoteDataSource.getMovies()

            override fun saveCallResult(data: List<MovieResultsItem>) {
                val movieList = ArrayList<MovieEntity>()

                for(response in data){
                    val movie = MovieEntity(
                            response.id,
                            response.title,
                            response.overview,
                            response.posterPath,
                            response.releaseDate,
                            response.voteAverage
                    )

                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getTVSeries(sortBy: String): LiveData<Resource<PagedList<TVSeriesEntity>>> {

        return object: NetworkBoundResource<PagedList<TVSeriesEntity>, List<TVSeriesResultsItem>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TVSeriesEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()

                return LivePagedListBuilder(localDataSource.getTVSeries(sortBy), config).build()
            }

            override fun shouldFetch(data: PagedList<TVSeriesEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TVSeriesResultsItem>>> =
                    remoteDataSource.getTVSeries()

            override fun saveCallResult(data: List<TVSeriesResultsItem>) {
                val tvSeriesList = ArrayList<TVSeriesEntity>()

                for(response in data){
                    val tvSeries = TVSeriesEntity(
                            response.id,
                            response.name,
                            response.overview,
                            response.posterPath,
                            response.firstAirDate,
                            response.voteAverage
                    )

                    tvSeriesList.add(tvSeries)
                }

                localDataSource.insertTVSeries(tvSeriesList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieDetails>> {

        return object : NetworkBoundResource<MovieDetails, DetailMovieResponse>(appExecutors){
            override fun loadFromDB(): LiveData<MovieDetails> =
                    localDataSource.getDetailMovieById(movieId)

            override fun shouldFetch(data: MovieDetails?): Boolean =
                    data?.movieEntity == null

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                    remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(data: DetailMovieResponse) {
                val genreList = ArrayList<MovieGenreEntity>()

                for(response in data.genres){
                    val genre = MovieGenreEntity(
                            movieId,
                            response.id,
                            response.name
                    )

                    genreList.add(genre)
                }

                localDataSource.insertMovieGenres(genreList)
            }
        }.asLiveData()
    }

    override fun getDetailTVSeries(tvSeriesId: Int): LiveData<Resource<TVSeriesDetails>> {

        return object : NetworkBoundResource<TVSeriesDetails, DetailTVSeriesResponse>(appExecutors){
            override fun loadFromDB(): LiveData<TVSeriesDetails> =
                    localDataSource.getDetailTVSeriesById(tvSeriesId)

            override fun shouldFetch(data: TVSeriesDetails?): Boolean =
                    data?.tvSeriesEntity == null

            override fun createCall(): LiveData<ApiResponse<DetailTVSeriesResponse>> =
                    remoteDataSource.getDetailTVSeries(tvSeriesId)

            override fun saveCallResult(data: DetailTVSeriesResponse) {
                val genreList = ArrayList<TVSeriesGenreEntity>()

                for(response in data.genres){
                    val genre = TVSeriesGenreEntity(
                            tvSeriesId,
                            response.id,
                            response.name
                    )

                    genreList.add(genre)
                }

                localDataSource.insertTVSeriesGenres(genreList)
            }
        }.asLiveData()
    }

    override fun getMovieGenres(movieId: Int): LiveData<Resource<List<MovieGenreEntity>>> {
        return object : NetworkBoundResource<List<MovieGenreEntity>, List<GenresItem>>(appExecutors){
            override fun loadFromDB(): LiveData<List<MovieGenreEntity>> =
                    localDataSource.getMovieGenresById(movieId)

            override fun shouldFetch(data: List<MovieGenreEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<GenresItem>>> =
                    remoteDataSource.getMovieGenres(movieId)

            override fun saveCallResult(data: List<GenresItem>) {
                val genreList = ArrayList<MovieGenreEntity>()

                for(response in data){
                    val genre = MovieGenreEntity(
                            movieId,
                            response.id,
                            response.name
                    )

                    genreList.add(genre)
                }

                localDataSource.insertMovieGenres(genreList)
            }

        }.asLiveData()
    }

    override fun getTVSeriesGenres(tvSeriesId: Int): LiveData<Resource<List<TVSeriesGenreEntity>>> {
        return object : NetworkBoundResource<List<TVSeriesGenreEntity>, List<GenresItem>>(appExecutors){
            override fun loadFromDB(): LiveData<List<TVSeriesGenreEntity>> =
                    localDataSource.getTVSeriesGenresById(tvSeriesId)

            override fun shouldFetch(data: List<TVSeriesGenreEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<GenresItem>>> =
                    remoteDataSource.getTVSeriesGenres(tvSeriesId)

            override fun saveCallResult(data: List<GenresItem>) {
                val genreList = ArrayList<TVSeriesGenreEntity>()

                for(response in data){
                    val genre = TVSeriesGenreEntity(
                            tvSeriesId,
                            response.id,
                            response.name
                    )

                    genreList.add(genre)
                }

                localDataSource.insertTVSeriesGenres(genreList)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovies(sortBy: String): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovies(sortBy), config).build()
    }

    override fun getFavoriteTVSeries(sortBy: String): LiveData<PagedList<TVSeriesEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()

        return LivePagedListBuilder(localDataSource.getFavoriteTVSeries(sortBy), config).build()
    }

    override fun setFavorite(movie: MovieEntity, state: Boolean) =
            appExecutors.diskIO().execute{ localDataSource.setMovieFavorite(movie, state)}

    override fun setFavorite(tvSeries: TVSeriesEntity, state: Boolean) =
            appExecutors.diskIO().execute{ localDataSource.setTVSeriesFavorite(tvSeries, state)}
}