package com.dicoding.picodiploma.movieapplication.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.utils.DataDummy
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun loadMovies(){
        // Check data is displayed or not
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
        // Scroll until last position
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie(){
        // Click the first list
        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // Check title is displayed
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        // Check matches in title
        onView(withId(R.id.text_title)).check(matches(withText(dummyMovie[0].title)))
        // Check image is displayed
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
    }
}