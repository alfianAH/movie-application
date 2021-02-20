package com.dicoding.picodiploma.movieapplication.utils

import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesDetails
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesEntity
import com.dicoding.picodiploma.movieapplication.data.source.local.entity.tvseries.TVSeriesGenreEntity
import com.dicoding.picodiploma.movieapplication.data.source.remote.response.*

object DataDummy {

    fun generateDummyMovies(): List<MovieEntity>{
        val movies = ArrayList<MovieEntity>()

        movies.add(MovieEntity(630566,
                        "Clouds",
                        "Young musician Zach Sobiech discovers his cancer has spread, leaving him just a few months to live. With limited time, he follows his dream and makes an album, unaware that it will soon be a viral music phenomenon.",
                        "/d0OdD1I8qAfETvE9Rp9Voq7R8LR.jpg",
                        "2020-10-09",
                        8.5,
                        false
            )
        )

        movies.add(MovieEntity(674,
                        "Harry Potter and the Goblet of Fire",
                        "Harry starts his fourth year at Hogwarts, competes in the treacherous Triwizard Tournament and faces the evil Lord Voldemort. Ron and Hermione help Harry manage the pressure – but Voldemort lurks, awaiting his chance to destroy Harry and all that he stands for.",
                        "/fECBtHlr0RB3foNHDiCBXeg9Bv9.jpg",
                        "2005-11-16",
                        7.8,
                        false
                )
        )

        return movies
    }

    fun generateDummyTVSeries(): List<TVSeriesEntity>{
        val tvSeries = ArrayList<TVSeriesEntity>()

        tvSeries.add(TVSeriesEntity(73055,
                "Attack on Titan: No Regrets",
                "Many years before becoming the famed captain of the Survey Corps, a young Levi struggles to survive in the capital's garbage dump, the Underground. As the boss of his own criminal operation, Levi attempts to get by with meager earnings while aided by fellow criminals, Isabel Magnolia and Farlan Church. With little hope for the future, Levi accepts a deal from the anti-expedition faction leader Nicholas Lobov, who promises the trio citizenship aboveground if they are able to successfully assassinate Erwin Smith, a squad leader of the Survey Corps.\n\nAs Levi and Erwin cross paths, Erwin acknowledges Levi's agility and skill and gives him the option to either become part of the expedition team, or be turned over to the Military Police, to atone for his crimes. Now closer to the man they are tasked to kill, the group plans to complete their mission and save themselves from a grim demise in the dim recesses of their past home. However, they are about to learn that the surface world is not as liberating as they had thought and that sometimes, freedom can come at a heavy price.\n\nBased on the popular spin-off manga of the same name, Attack on Titan: No Regrets illustrates the encounter between two of Attack on Titan's pivotal characters, as well as the events of the 23rd expedition beyond the walls.",
                "/xGdz67d5WHIU7kIqVHO2TxIpmhZ.jpg",
                "2014-12-09",
                8.9,
                false
        ))

        tvSeries.add(TVSeriesEntity(65930,
                "My Hero Academia",
                "In a world where eighty percent of the population has some kind of super-powered Quirk, Izuku was unlucky enough to be born completely normal. But that won’t stop him from enrolling in a prestigious hero academy. Now, he’ll get his first taste of brutal rivalry from other schools as he braves the cutthroat, no-holds-barred provisional license exam.",
                "/he1aoyFEPYAMsjGOVGfObbEIzid.jpg",
                "2016-04-03",
                8.9,
                false
        ))

        return tvSeries
    }

    fun generateRemoteDummyMovies(): List<MovieResultsItem>{
        val movieResponse = ArrayList<MovieResultsItem>()

        movieResponse.add(MovieResultsItem(
            "Young musician Zach Sobiech discovers his cancer has spread, leaving him just a few months to live. With limited time, he follows his dream and makes an album, unaware that it will soon be a viral music phenomenon.",
            "Clouds",
            "/d0OdD1I8qAfETvE9Rp9Voq7R8LR.jpg",
            "2020-10-09",
            8.5,
            630566
        ))

        movieResponse.add(MovieResultsItem("Harry starts his fourth year at Hogwarts, competes in the treacherous Triwizard Tournament and faces the evil Lord Voldemort. Ron and Hermione help Harry manage the pressure – but Voldemort lurks, awaiting his chance to destroy Harry and all that he stands for.",
                "Harry Potter and the Goblet of Fire",
                "/fECBtHlr0RB3foNHDiCBXeg9Bv9.jpg",
                "2005-11-16",
                7.8,
                674

        ))

        return movieResponse
    }

    fun generateRemoteDummyTVSeries(): List<TVSeriesResultsItem>{
        val tvSeriesResponse = ArrayList<TVSeriesResultsItem>()

        tvSeriesResponse.add(TVSeriesResultsItem("2014-12-09",
                "Many years before becoming the famed captain of the Survey Corps, a young Levi struggles to survive in the capital's garbage dump, the Underground. As the boss of his own criminal operation, Levi attempts to get by with meager earnings while aided by fellow criminals, Isabel Magnolia and Farlan Church. With little hope for the future, Levi accepts a deal from the anti-expedition faction leader Nicholas Lobov, who promises the trio citizenship aboveground if they are able to successfully assassinate Erwin Smith, a squad leader of the Survey Corps.\n\nAs Levi and Erwin cross paths, Erwin acknowledges Levi's agility and skill and gives him the option to either become part of the expedition team, or be turned over to the Military Police, to atone for his crimes. Now closer to the man they are tasked to kill, the group plans to complete their mission and save themselves from a grim demise in the dim recesses of their past home. However, they are about to learn that the surface world is not as liberating as they had thought and that sometimes, freedom can come at a heavy price.\n\nBased on the popular spin-off manga of the same name, Attack on Titan: No Regrets illustrates the encounter between two of Attack on Titan's pivotal characters, as well as the events of the 23rd expedition beyond the walls.",
                "/xGdz67d5WHIU7kIqVHO2TxIpmhZ.jpg",
                8.9,
                "Attack on Titan: No Regrets",
                73055

        ))

        tvSeriesResponse.add(TVSeriesResultsItem("2016-04-03",
                "In a world where eighty percent of the population has some kind of super-powered Quirk, Izuku was unlucky enough to be born completely normal. But that won’t stop him from enrolling in a prestigious hero academy. Now, he’ll get his first taste of brutal rivalry from other schools as he braves the cutthroat, no-holds-barred provisional license exam.",
                "/he1aoyFEPYAMsjGOVGfObbEIzid.jpg",
                8.9,
                "My Hero Academia",
                65930
        ))

        return tvSeriesResponse
    }

    /**
     * Genre Movie[0]: Clouds
     */
    fun generateDummyMovieGenres(movieId: Int): List<MovieGenreEntity>{
        val genres = ArrayList<MovieGenreEntity>()

        genres.add(MovieGenreEntity(movieId,
                10402,
                "Music"
        ))

        genres.add(MovieGenreEntity(movieId,
                18,
                "Drama"
        ))

        genres.add(MovieGenreEntity(movieId,
                10749,
                "Romance"
        ))

        return genres
    }

    /**
     * Genre TV Series[0]: AOT
     */
    fun generateDummyTVSeriesGenres(tvSeriesId: Int): List<TVSeriesGenreEntity>{
        val genres = ArrayList<TVSeriesGenreEntity>()

        genres.add(TVSeriesGenreEntity(tvSeriesId,
                10765,
                "Sci-Fi & Fantasy"
        ))

        genres.add(TVSeriesGenreEntity(tvSeriesId,
                10759,
                "Action & Adventure"
        ))

        genres.add(TVSeriesGenreEntity(tvSeriesId,
                16,
                "Animation"
        ))

        return genres
    }

    /**
     * Genre Movie[0]: Demon Slayer
     */
    fun generateRemoteDummyMovieGenres(movieId: Int): List<GenresItem>{
        val genres = ArrayList<GenresItem>()

        genres.add(GenresItem("Animation",
                16
        ))

        genres.add(GenresItem("Action",
                29
        ))

        genres.add(GenresItem("Adventure",
                12
        ))

        genres.add(GenresItem("Fantasy",
                14
        ))

        genres.add(GenresItem("Drama",
                18
        ))

        return genres
    }

    /**
     * Genre TV Series[0]: AOT
     */
    fun generateRemoteDummyTVSeriesGenres(tvSeriesId: Int): List<GenresItem>{
        val genres = ArrayList<GenresItem>()

        genres.add(GenresItem("Sci-Fi & Fantasy",
                10765
        ))

        genres.add(GenresItem("Action & Adventure",
                10759
        ))

        genres.add(GenresItem("Animation",
                16
        ))

        return genres
    }

    fun generateDummyDetailMovie(movie: MovieEntity, isFavorite: Boolean): MovieDetails{
        movie.isFavorite = isFavorite
        return MovieDetails(movie, generateDummyMovieGenres(movie.id))
    }

    fun generateDummyDetailTVSeries(tvSeries: TVSeriesEntity, isFavorite: Boolean): TVSeriesDetails{
        tvSeries.isFavorite = isFavorite
        return TVSeriesDetails(tvSeries, generateDummyTVSeriesGenres(tvSeries.id))
    }
}