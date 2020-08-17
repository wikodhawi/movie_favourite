package com.stickearn.moviefavourite.view.main.activity

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import com.stickearn.moviefavourite.R
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkVisibleInvisibleMainActivity() {
        // Please make sure your screen device on
        Espresso.onView(withId(R.id.shimmerPopularMovie)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.shimmerNowPlayingMovie)).check(matches(not(isDisplayed())))
        Espresso.onView(withId(R.id.shimmerTopRatedMovie)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rcyPopularMovie)).check(matches(not(isDisplayed())))
        Espresso.onView(withId(R.id.rcyNowPlaying)).check(matches(not(isDisplayed())))
        Espresso.onView(withId(R.id.rcyTopRatedMovie)).check(matches(not(isDisplayed())))
    }
}