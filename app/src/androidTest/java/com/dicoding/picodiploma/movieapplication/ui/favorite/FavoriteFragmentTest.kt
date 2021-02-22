package com.dicoding.picodiploma.movieapplication.ui.favorite

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.ViewPagerActions
import androidx.test.espresso.matcher.ViewMatchers
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.ui.home.HomeActivity
import com.dicoding.picodiploma.movieapplication.utils.EspressoIdlingResources
import org.hamcrest.core.AllOf
import org.hamcrest.core.Is
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Test

class FavoriteFragmentTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResources.espressoTestIdlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.espressoTestIdlingResource)
    }

    @Test
    fun loadFavoriteMovies(){
        // Change bottom navigation to favorite
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(R.string.favorite))).perform(ViewActions.click())

        // Check data is displayed or not
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
    }

    @Test
    fun loadFavoriteTVSeries(){
        // Change bottom navigation to favorite
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(R.string.favorite))).perform(ViewActions.click())

        // Scroll view pager to tv series
        Espresso.onView(ViewMatchers.withId(R.id.view_pager)).perform(ViewPagerActions.scrollRight(true))

        // Check data is displayed or not
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
    }

    @Test
    fun sortFavoriteMovies(){
        // Change bottom navigation to favorite
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(R.string.favorite))).perform(ViewActions.click())

        // Click spinner
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.sort_dropdown))).perform(ViewActions.click())

        // Sort by rating
        Espresso.onData(AllOf.allOf(Is.`is`(IsInstanceOf.instanceOf(String::class.java)), Is.`is`("Rating"))).perform(ViewActions.click())

        // Check data is displayed or not
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
    }

    @Test
    fun sortFavoriteTVSeries(){
        // Change bottom navigation to favorite
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(R.string.favorite))).perform(ViewActions.click())

        // Scroll view pager to tv series
        Espresso.onView(ViewMatchers.withId(R.id.view_pager)).perform(ViewPagerActions.scrollRight(true))

        // Click spinner
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.sort_dropdown))).perform(ViewActions.click())

        // Sort by rating
        Espresso.onData(AllOf.allOf(Is.`is`(IsInstanceOf.instanceOf(String::class.java)), Is.`is`("Rating"))).perform(ViewActions.click())

        // Check data is displayed or not
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(R.id.rv_list)))
    }
}