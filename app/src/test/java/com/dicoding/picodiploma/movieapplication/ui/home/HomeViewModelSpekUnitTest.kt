package com.dicoding.picodiploma.movieapplication.ui.home

import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.spekframework.spek2.Spek

@RunWith(JUnitPlatform::class)
class HomeViewModelSpekUnitTest : Spek({
    lateinit var viewModel: HomeViewModel

    group("Home View Model"){
        group("Get Movie"){
            beforeEachTest {
                viewModel = HomeViewModel()
            }

            test("Get movie"){
                val movieEntities = viewModel.getMovies()

                Assert.assertNotNull(movieEntities)
                Assert.assertEquals(10, movieEntities.size)
            }
        }

        group("Get TV Series"){
            test("Get TV Series"){
                val tvSeriesEntities = viewModel.getTVSeries()

                Assert.assertNotNull(tvSeriesEntities)
                Assert.assertEquals(10, tvSeriesEntities.size)
            }
        }
    }
})