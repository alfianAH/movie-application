package com.dicoding.picodiploma.movieapplication.utils

import com.dicoding.picodiploma.movieapplication.data.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.TVSeriesEntity

object DataDummy {

    fun generateDummyMovies(): List<MovieEntity>{
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
            "Wonder Woman 1984",
                2020,
            "Diana must contend with a work colleague and businessman, whose desire for extreme wealth sends the world down a path of destruction, after an ancient artifact that grants wishes goes missing.",
            "Action, Adventure, Fantasy",
            "Patty Jenkins",
            "https://m.media-amazon.com/images/M/MV5BNWY2NWE0NWEtZGUwMC00NWMwLTkyNzUtNmIxMmIyYzA0MjNiXkEyXkFqcGdeQXVyMTA2OTQ3MTUy._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
            "Soul",
            2020,
            "A musician who has lost his passion for music is transported out of his body and must find his way back with the help of an infant soul learning about herself. ",
            "Animation, Adventure, Comedy",
            "Pete Docter, Kemp Powers (co-director) ",
            "https://m.media-amazon.com/images/M/MV5BZGE1MDg5M2MtNTkyZS00MTY5LTg1YzUtZTlhZmM1Y2EwNmFmXkEyXkFqcGdeQXVyNjA3OTI0MDc@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
            "Tenet",
            2020,
            "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time. ",
            "Action, Sci-Fi, Thriller",
            "Christopher Nolan",
            "https://m.media-amazon.com/images/M/MV5BYzg0NGM2NjAtNmIxOC00MDJmLTg5ZmYtYzM0MTE4NWE2NzlhXkEyXkFqcGdeQXVyMTA4NjE0NjEy._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
            "",
            0,
            "",
            "",
            "",
            ""
        ))

        movies.add(MovieEntity(
            "Shadow in the Cloud",
            2020,
            "A female WWII pilot traveling with top secret documents on a B-17 Flying Fortress encounters an evil presence on board the flight.",
            "Action, Horror, War",
            "Roseanne Liang",
            "https://m.media-amazon.com/images/M/MV5BNWNiNGQyMzUtN2VmMi00NDI2LWI3NGUtMTEwZGQxYzFjZTNjXkEyXkFqcGdeQXVyMTEyNDk3MjY3._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
            "Wonder Woman",
            2017,
            "When a pilot crashes and tells of conflict in the outside world, Diana, an Amazonian warrior in training, leaves home to fight a war, discovering her full powers and true destiny. ",
            "Action, Adventure, Fantasy",
            "Patty Jenkins",
            "https://m.media-amazon.com/images/M/MV5BMTYzODQzYjQtNTczNC00MzZhLTg1ZWYtZDUxYmQ3ZTY4NzA1XkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
            "Promising Young Woman",
            2020,
            "A young woman, traumatized by a tragic event in her past, seeks out vengeance against those who cross her path.",
            "Crime, Drama, Thriller",
            "Emerald Fennell",
            "https://m.media-amazon.com/images/M/MV5BZDViMzBiNGMtZTIyNS00NzI4LWE3NDMtNmM1NDk0NzBlMWRlXkEyXkFqcGdeQXVyMTA2MDU0NjM5._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
            "Mulan",
            2020,
            "A young Chinese maiden disguises herself as a male warrior in order to save her father. ",
            "Action, Adventure, Drama",
            "Niki Caro",
            "https://m.media-amazon.com/images/M/MV5BNDliY2E1MjUtNzZkOS00MzJlLTgyOGEtZDg4MTI1NzZkMTBhXkEyXkFqcGdeQXVyNjMwMzc3MjE@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
            "We Can Be Heroes",
            2020,
            "When alien invaders kidnap Earth's superheroes, their children must team up and learn to work together if they want to save their parents and the world. ",
            "Action, Comedy, Drama",
            "Robert Rodriguez",
            "https://m.media-amazon.com/images/M/MV5BYmU3OGFhZDItMjhlZi00YjQwLWJlOTItYzlhOGEwNGU3NjIwXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
            "The Midnight Sky",
            2020,
            "This post-apocalyptic tale follows Augustine, a lonely scientist in the Arctic, as he races to stop Sully and her fellow astronauts from returning home to a mysterious global catastrophe.",
            "Drama, Fantasy, Sci-Fi ",
            "George Clooney",
            "https://www.imdb.com/title/tt10539608/mediaviewer/rm925096961?ref_=tt_ov_i"
        ))

        movies.add(MovieEntity(
            "Love Actually",
            2003,
            "Follows the lives of eight very different couples in dealing with their love lives in various loosely interrelated tales all set during a frantic month before Christmas in London, England. ",
            "Comedy, Drama, Romance",
            "Richard Curtis",
            "https://www.imdb.com/title/tt0314331/mediaviewer/rm3436419328?ref_=tt_ov_i"
        ))

        return movies
    }

    fun generateDummyTVSeries(): List<TVSeriesEntity>{
        val tvSeries = ArrayList<TVSeriesEntity>()

        tvSeries.add(TVSeriesEntity(
            "Bridgerton",
            2020,
            "Wealth, lust, and betrayal set against the backdrop of Regency-era England, seen through the eyes of the powerful Bridgerton family.",
            "Drama, Romance",
            "Chris Van Dusen",
            "https://m.media-amazon.com/images/M/MV5BYjIxMzZhMTMtNDQ1Mi00OTMwLTk2M2ItYzA0YmNjNDFlOTdhXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        tvSeries.add(TVSeriesEntity(
            "The Mandalorian",
            2019,
            "The travels of a lone bounty hunter in the outer reaches of the galaxy, far from the authority of the New Republic.",
            "Action, Adventure, Sci-Fi ",
            "Jon Favreau",
            "https://www.imdb.com/title/tt8111088/mediaviewer/rm1049273857?ref_=tt_ov_i"
        ))

        tvSeries.add(TVSeriesEntity(
            "Vikings",
            2013,
            "Vikings transports us to the brutal and mysterious world of Ragnar Lothbrok, a Viking warrior and farmer who yearns to explore - and raid - the distant shores across the ocean. ",
            "Action, Adventure, Drama",
            "Michael Hirst",
            "https://www.imdb.com/title/tt2306299/mediaviewer/rm3031749377?ref_=tt_ov_i"
        ))

        tvSeries.add(TVSeriesEntity(
            "The Queen's Gambit",
            2020,
            "Orphaned at the tender age of nine, prodigious introvert Beth Harmon discovers and masters the game of chess in 1960s USA. But child stardom comes at a price. ",
            "Drama",
            "Scott Frank, Allan Scott",
            "https://www.imdb.com/title/tt10048342/mediaviewer/rm1650697985?ref_=tt_ov_i"
        ))

        tvSeries.add(TVSeriesEntity(
            "Death to 2020",
            2020,
            "Take a look back at the mad glory of the year 2020 in this comedic retrospective.",
            "Comedy",
            "Al Campbell, Alice Mathias",
            "https://m.media-amazon.com/images/M/MV5BMDQ0MmIwYWYtMTEzYS00MDg0LThiMTMtNTA1OTA0ODkxNDc2XkEyXkFqcGdeQXVyODc0OTEyNDU@._V1_UY268_CR4,0,182,268_AL_.jpg"
        ))

        tvSeries.add(TVSeriesEntity(
            "The Crown ",
            2016,
            "Follows the political rivalries and romance of Queen Elizabeth II's reign and the events that shaped the second half of the twentieth century. ",
            "Biography, Drama, History",
            "Peter Morgan",
            "https://www.imdb.com/title/tt4786824/mediaviewer/rm2835667201?ref_=tt_ov_i"
        ))

        tvSeries.add(TVSeriesEntity(
            "Game of Thrones  ",
            2011,
            "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia. ",
            "Action, Adventure, Drama",
            "David Benioff, D.B. Weiss",
            "https://m.media-amazon.com/images/M/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_UY268_CR7,0,182,268_AL_.jpg"
        ))

        tvSeries.add(TVSeriesEntity(
            "The Stand  ",
            2020,
            "After the world is in ruins, due to a man-made plague, a battle of Biblical proportions ensues between the survivors. ",
            "Adventure, Drama, Fantasy",
            "Josh Boone, Benjamin Cavell",
            "https://m.media-amazon.com/images/M/MV5BMjNjNDdhZGUtZWZmZS00ZDk3LTg0MzQtZmU5MDExY2Q1ZjYzXkEyXkFqcGdeQXVyNjEwNTM2Mzc@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        tvSeries.add(TVSeriesEntity(
            "Yellowstone",
            2018,
            "A ranching family in Montana faces off against others encroaching on their land. ",
            "Drama, Western",
            "Taylor Sheridan, John Linson",
            "https://www.imdb.com/title/tt4236770/mediaviewer/rm908442881?ref_=tt_ov_i"
        ))

        tvSeries.add(TVSeriesEntity(
            "The Expanse",
            2015,
            "In the 24th century, a disparate band of antiheroes unravel a vast conspiracy that threatens the Solar System's fragile state of cold war.",
            "Drama, Mystery, Sci-Fi ",
            "Daniel Abraham, Mark Fergus, Ty Franck, Hawk Ostby ",
            "https://www.imdb.com/title/tt3230854/mediaviewer/rm2578567681?ref_=tt_ov_i"
        ))

        return tvSeries
    }
}