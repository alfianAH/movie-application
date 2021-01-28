package com.dicoding.picodiploma.movieapplication.utils

import com.dicoding.picodiploma.movieapplication.data.source.remote.response.*

object DataDummy {

    fun generateDummyMovies(): List<MovieResultsItem>{
        val movies = ArrayList<MovieResultsItem>()

        movies.add(0, MovieResultsItem(
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            "Wonder Woman 1984",
            arrayListOf(14, 28, 12),
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "2020-12-16",
            7.1,
            464052
        ))

        movies.add(1, MovieResultsItem(
            "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
            "Soul",
            arrayListOf(16, 38, 18),
            "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
            "2020-12-25",
            8.3,
            508442
        ))

        return movies
    }

    fun generateDummyTVSeries(): List<TVSeriesResultsItem>{
        val tvSeries = ArrayList<TVSeriesResultsItem>()

        tvSeries.add(0, TVSeriesResultsItem(
            "2004-05-10",
            "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.",
            arrayListOf(16, 35),
            "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
            9.4,
            "I Am Not an Animal",
            100
        ))

        tvSeries.add(1, TVSeriesResultsItem(
            "2019-07-11",
            "Tightly clutching his Gibson guitar, Mafuyu Satou steps out of his dark apartment to begin another day of his high school life. While taking a nap in a quiet spot on the gymnasium staircase, he has a chance encounter with fellow student Ritsuka Uenoyama, who berates him for letting his guitar's strings rust and break. Noticing Uenoyama's knowledge of the instrument, Satou pleads for him to fix it and to teach him how to play. Uenoyama eventually agrees and invites him to sit in on a jam session with his two band mates: bassist Haruki Nakayama and drummer Akihiko Kaji.\\n\\nSatou's voice is strikingly beautiful, filling Uenoyama with the determination to make Satou the lead singer of the band. Though reticent at first, Satou takes the offer after an emotional meeting with an old friend. With the support of his new friends, Satou must not only learn how to play guitar, but also come to terms with the mysterious circumstances that led him to be its owner.",
            arrayListOf(16, 18),
            "/pdDCcAq8RNSZNk81PXYoHNUPHjn.jpg",
            9.2,
            "Given",
            88040
        ))

        return tvSeries
    }

    fun generateDummyDetailMovie(): DetailMovieResponse{
        return DetailMovieResponse(
                "Wonder Woman 1984",
                arrayListOf(
                        GenresItem("Fantasy", 14),
                        GenresItem("Action", 28),
                        GenresItem("Adventure", 12)
                ),
                464052,
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "2020-12-16",
                7.1,
                "Released"
        )
    }

    fun generateDummyDetailTVSeries(): DetailTVSeriesResponse{
        return DetailTVSeriesResponse(
                arrayListOf(
                        GenresItem("Animation", 16),
                        GenresItem("Mystery", 9648),
                        GenresItem("Sci-Fi & Fantasy", 10765),
                        GenresItem("Action & Adventure", 10759),
                        GenresItem("Drama", 18)
                ),
                83097,
                "2019-01-11",
                "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \"Mama,\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again.\n\nHowever, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama.",
                "/yxdeII5tI8qqiERcMxjW9DfB6Gz.jpg",
                9.2,
                "The Promised Neverland",
                "Returning Series"
        )
    }
}