package com.example.uscrecapp_team28;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void onLoginSuccess() {
        onView(withId(R.id.username)).perform(typeText("test_login"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test_login"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.signinbtn)).perform(click());
        onView(withId(R.id.wrong)).check(doesNotExist());
    }

    @Test
    public void onLoginFail() {
        onView(withId(R.id.username)).perform(typeText("test_login"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test_login_wrong"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.signinbtn)).perform(click());
        onView(withId(R.id.wrong)).check(matches(withText("Wrong username/password. Try again.")));
    }

    @Test
    public void onLoginClick() {
    }
}