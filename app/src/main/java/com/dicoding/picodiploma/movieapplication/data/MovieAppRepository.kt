package com.dicoding.picodiploma.movieapplication.data

import androidx.lifecycle.LiveData
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

class MovieAppRepository private constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
):
    MovieAppDataSource {
    
    companion object{
        @Volatile
        private var instance: MovieAppRepository? = null
        
        fun getInstance(remoteDataSource: RemoteDataSource,
                        localDataSource: LocalDataSource,
                        appExecutors: AppExecutors
        ): MovieAppRepository =
            instance ?: synchronized(this){
                instance ?: MovieAppRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }
    
    override fun getMovies(): LiveData<Resource<List<MovieEntity>>> {

        return object: NetworkBoundResource<List<MovieEntity>, List<MovieResultsItem>>(appExecutors){
            override fun loadFromDB(): LiveData<List<MovieEntity>> =
                    localDataSource.getMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
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

    override fun getTVSeries(): LiveData<Resource<List<TVSeriesEntity>>> {

        return object: NetworkBoundResource<List<TVSeriesEntity>, List<TVSeriesResultsItem>>(appExecutors){
            override fun loadFromDB(): LiveData<List<TVSeriesEntity>> =
                    localDataSource.getTVSeries()

            override fun shouldFetch(data: List<TVSeriesEntity>?): Boolean =
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

        return object : NetworkBoundResource<MovieDetails, List<GenresItem>>(appExecutors){
            override fun loadFromDB(): LiveData<MovieDetails> =
                    localDataSource.getDetailMovieById(movieId)

            override fun shouldFetch(data: MovieDetails?): Boolean =
                    data?.movieEntity == null

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

    override fun getDetailTVSeries(tvSeriesId: Int): LiveData<Resource<TVSeriesDetails>> {

        return object : NetworkBoundResource<TVSeriesDetails, List<GenresItem>>(appExecutors){
            override fun loadFromDB(): LiveData<TVSeriesDetails> =
                    localDataSource.getDetailTVSeriesById(tvSeriesId)

            override fun shouldFetch(data: TVSeriesDetails?): Boolean =
                    data?.tvSeriesEntity == null

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

    override fun getFavoriteMovies(): LiveData<List<MovieEntity>> =
            localDataSource.getFavoriteMovies()

    override fun getFavoriteTVSeries(): LiveData<List<TVSeriesEntity>> =
            localDataSource.getFavoriteTVSeries()

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) =
            appExecutors.diskIO().execute{ localDataSource.setMovieFavorite(movie, state)}

    override fun setFavoriteTVSeries(tvSeries: TVSeriesEntity, state: Boolean) =
            appExecutors.diskIO().execute{ localDataSource.setTVSeriesFavorite(tvSeries, state)}
}