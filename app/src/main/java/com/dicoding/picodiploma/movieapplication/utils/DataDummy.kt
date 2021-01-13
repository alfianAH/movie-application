package com.dicoding.picodiploma.movieapplication.utils

import com.dicoding.picodiploma.movieapplication.data.MovieEntity
import com.dicoding.picodiploma.movieapplication.data.TVSeriesEntity

object DataDummy {

    fun generateDummyMovies(): List<MovieEntity>{
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                    1,
            "Wonder Woman 1984",
                2020,
            "Diana must contend with a work colleague and businessman, whose desire for extreme wealth sends the world down a path of destruction, after an ancient artifact that grants wishes goes missing.",
            "Action, Adventure, Fantasy",
            "Patty Jenkins",
            "https://m.media-amazon.com/images/M/MV5BNWY2NWE0NWEtZGUwMC00NWMwLTkyNzUtNmIxMmIyYzA0MjNiXkEyXkFqcGdeQXVyMTA2OTQ3MTUy._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
                2,
            "Soul",
            2020,
            "A musician who has lost his passion for music is transported out of his body and must find his way back with the help of an infant soul learning about herself. ",
            "Animation, Adventure, Comedy",
            "Pete Docter, Kemp Powers (co-director) ",
            "https://m.media-amazon.com/images/M/MV5BZGE1MDg5M2MtNTkyZS00MTY5LTg1YzUtZTlhZmM1Y2EwNmFmXkEyXkFqcGdeQXVyNjA3OTI0MDc@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
                3,
            "Tenet",
            2020,
            "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time. ",
            "Action, Sci-Fi, Thriller",
            "Christopher Nolan",
            "https://m.media-amazon.com/images/M/MV5BYzg0NGM2NjAtNmIxOC00MDJmLTg5ZmYtYzM0MTE4NWE2NzlhXkEyXkFqcGdeQXVyMTA4NjE0NjEy._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
                4,
            "Shadow in the Cloud",
            2020,
            "A female WWII pilot traveling with top secret documents on a B-17 Flying Fortress encounters an evil presence on board the flight.",
            "Action, Horror, War",
            "Roseanne Liang",
            "https://m.media-amazon.com/images/M/MV5BNWNiNGQyMzUtN2VmMi00NDI2LWI3NGUtMTEwZGQxYzFjZTNjXkEyXkFqcGdeQXVyMTEyNDk3MjY3._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
                5,
            "Wonder Woman",
            2017,
            "When a pilot crashes and tells of conflict in the outside world, Diana, an Amazonian warrior in training, leaves home to fight a war, discovering her full powers and true destiny. ",
            "Action, Adventure, Fantasy",
            "Patty Jenkins",
            "https://m.media-amazon.com/images/M/MV5BMTYzODQzYjQtNTczNC00MzZhLTg1ZWYtZDUxYmQ3ZTY4NzA1XkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
                6,
            "Promising Young Woman",
            2020,
            "A young woman, traumatized by a tragic event in her past, seeks out vengeance against those who cross her path.",
            "Crime, Drama, Thriller",
            "Emerald Fennell",
            "https://m.media-amazon.com/images/M/MV5BZDViMzBiNGMtZTIyNS00NzI4LWE3NDMtNmM1NDk0NzBlMWRlXkEyXkFqcGdeQXVyMTA2MDU0NjM5._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
                7,
            "Mulan",
            2020,
            "A young Chinese maiden disguises herself as a male warrior in order to save her father. ",
            "Action, Adventure, Drama",
            "Niki Caro",
            "https://m.media-amazon.com/images/M/MV5BNDliY2E1MjUtNzZkOS00MzJlLTgyOGEtZDg4MTI1NzZkMTBhXkEyXkFqcGdeQXVyNjMwMzc3MjE@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
                8,
            "We Can Be Heroes",
            2020,
            "When alien invaders kidnap Earth's superheroes, their children must team up and learn to work together if they want to save their parents and the world. ",
            "Action, Comedy, Drama",
            "Robert Rodriguez",
            "https://m.media-amazon.com/images/M/MV5BYmU3OGFhZDItMjhlZi00YjQwLWJlOTItYzlhOGEwNGU3NjIwXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
                9,
            "The Lion King",
            2019,
            "After the murder of his father, a young lion prince flees his kingdom only to learn the true meaning of responsibility and bravery. ",
            " Animation, Adventure, Drama",
            "Jon Favreau",
            "https://m.media-amazon.com/images/M/MV5BMjIwMjE1Nzc4NV5BMl5BanBnXkFtZTgwNDg4OTA1NzM@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        movies.add(MovieEntity(
                10,
            "The Godfather",
            1972,
            "An organized crime dynasty's aging patriarch transfers control of his clandestine empire to his reluctant son.",
            "Crime, Drama",
            "Francis Ford Coppola",
            "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY268_CR3,0,182,268_AL_.jpg"
        ))

        return movies
    }

    fun generateDummyTVSeries(): List<TVSeriesEntity>{
        val tvSeries = ArrayList<TVSeriesEntity>()

        tvSeries.add(TVSeriesEntity(
                1,
            "Bridgerton",
            2020,
            "Wealth, lust, and betrayal set against the backdrop of Regency-era England, seen through the eyes of the powerful Bridgerton family.",
            "Drama, Romance",
            "Chris Van Dusen",
            "https://m.media-amazon.com/images/M/MV5BYjIxMzZhMTMtNDQ1Mi00OTMwLTk2M2ItYzA0YmNjNDFlOTdhXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        tvSeries.add(TVSeriesEntity(
                2,
            "The Flight Attendant",
            2020,
            "A reckless flight attendant with an alcoholism problem, wakes up in the wrong hotel, in the wrong bed, with a dead man - and no idea what happened. When questioned by FBI agents and still unable to piece the night together, she begins to wonder if she could be the killer.",
            "Comedy, Drama, Mystery",
            "Steve Yockey",
            "https://m.media-amazon.com/images/M/MV5BN2U5Y2NiMDgtNDg4NS00ZTU2LWFlMmQtMjA5YjBkNDg4NGQ5XkEyXkFqcGdeQXVyMDM2NDM2MQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        tvSeries.add(TVSeriesEntity(
                3,
            "The Undoing",
            2020,
            "A modern twist to a classical \"whodunnit\" tale, when the life of a wealthy New York therapist turns upside down after she and her family get involved with a murder case.",
            "Crime, Drama, Mystery",
            "David E. Kelley",
            "https://m.media-amazon.com/images/M/MV5BNzY5YTcxMWYtYjFkZi00ZmI3LThmYjgtMzY0YjQzZWFkNzQ4XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        tvSeries.add(TVSeriesEntity(
                4,
            "Shameless",
            2011,
            " A scrappy, feisty, fiercely loyal Chicago family makes no apologies.",
            "Comedy, Drama",
            "John Wells, Paul Abbott",
                "https://m.media-amazon.com/images/M/MV5BZGFiOGY1MjAtNWM2Yy00YzBlLTkwODktMTZiOWMyZmNmZmZhXkEyXkFqcGdeQXVyOTA3MTMyOTk@._V1_UY268_CR9,0,182,268_AL_.jpg"
        ))

        tvSeries.add(TVSeriesEntity(
                5,
            "Death to 2020",
            2020,
            "Take a look back at the mad glory of the year 2020 in this comedic retrospective.",
            "Comedy",
            "Al Campbell, Alice Mathias",
            "https://m.media-amazon.com/images/M/MV5BMDQ0MmIwYWYtMTEzYS00MDg0LThiMTMtNTA1OTA0ODkxNDc2XkEyXkFqcGdeQXVyODc0OTEyNDU@._V1_UY268_CR4,0,182,268_AL_.jpg"
        ))

        tvSeries.add(TVSeriesEntity(
                6,
            "The Wilds",
            2020,
            "A group of teens must survive after a crash leaves them stranded. There's just one twist to this thrilling drama - these girls did not end up on this island by accident.",
            "Adventure, Drama, Mystery",
            "Sarah Streicher",
            "https://m.media-amazon.com/images/M/MV5BMzgzZDA2NmEtZTAwZi00ZTY3LWFiYTktNTdjYTBkNTY1N2E4XkEyXkFqcGdeQXVyMDM2NDM2MQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        tvSeries.add(TVSeriesEntity(
                7,
            "Game of Thrones  ",
            2011,
            "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia. ",
            "Action, Adventure, Drama",
            "David Benioff, D.B. Weiss",
            "https://m.media-amazon.com/images/M/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_UY268_CR7,0,182,268_AL_.jpg"
        ))

        tvSeries.add(TVSeriesEntity(
                8,
            "The Stand  ",
            2020,
            "After the world is in ruins, due to a man-made plague, a battle of Biblical proportions ensues between the survivors. ",
            "Adventure, Drama, Fantasy",
            "Josh Boone, Benjamin Cavell",
            "https://m.media-amazon.com/images/M/MV5BMjNjNDdhZGUtZWZmZS00ZDk3LTg0MzQtZmU5MDExY2Q1ZjYzXkEyXkFqcGdeQXVyNjEwNTM2Mzc@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        tvSeries.add(TVSeriesEntity(
                9,
            "Fargo",
            2014,
            "Various chronicles of deception, intrigue and murder in and around frozen Minnesota. Yet all of these tales mysteriously lead back one way or another to Fargo, North Dakota. ",
            "Crime, Drama, Thriller",
            "Noah Hawley",
            "https://m.media-amazon.com/images/M/MV5BN2NiMGE5M2UtNWNlNC00N2Y4LTkwOWUtMDlkMzEwNTcyOTcyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UY268_CR1,0,182,268_AL_.jpg"
        ))

        tvSeries.add(TVSeriesEntity(
                10,
            "The Watch",
            2020,
            "Based on the characters from Terry Pratchett's Discworld novels, A group of misfit cops rise up from decades of helplessness to save their corrupt city from catastrophe. ",
            "Action, Adventure, Comedy",
            "Simon Allen",
            "https://m.media-amazon.com/images/M/MV5BYzMwMGM1MWItNjAwMC00YmY3LWFmNjQtMGM1N2YzODliOThmXkEyXkFqcGdeQXVyNjEwNTM2Mzc@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        return tvSeries
    }
}