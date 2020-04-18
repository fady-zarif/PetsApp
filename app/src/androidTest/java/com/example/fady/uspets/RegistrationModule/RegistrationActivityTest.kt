package com.example.fady.uspets.RegistrationModule

import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementAdapter
import com.example.fady.uspets.R
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith

class RegistrationActivityTest {
    @Test
    fun testSignIn() {
        val activity = ActivityScenario.launch(RegistrationActivity::class.java)
        onView(withId(R.id.tvSignIn)).perform(click())
        onView(withId(R.id.etDialogEmail)).perform(typeText("User Email"))
        onView(withId(R.id.etDialogPassword)).perform(typeText("User Password"))
        onView(withId(R.id.btnSignIn)).perform(click())
        Thread.sleep(8000)
        onView(withId(R.id.MyRcyclerview)).perform(actionOnItemAtPosition<AdvertisementAdapter.AdvertisementViewHolder>(0, click()))

    }
}