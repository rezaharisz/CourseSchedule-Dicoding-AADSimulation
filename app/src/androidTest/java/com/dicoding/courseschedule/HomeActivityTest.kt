package com.dicoding.courseschedule

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dicoding.courseschedule.ui.home.HomeActivity
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    @Before
    fun setup(){
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun testUI(){
        onView(withId(R.id.action_add))
            .perform(ViewActions.click())
    }
}