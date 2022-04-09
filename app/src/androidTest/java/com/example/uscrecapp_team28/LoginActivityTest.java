package com.example.uscrecapp_team28;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.content.Intent;

import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
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
import static androidx.test.ext.truth.content.IntentSubject.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule =
            new ActivityTestRule<>(LoginActivity.class);
    @Rule
    public IntentsTestRule<MapActivity> intentsTestRule =
            new IntentsTestRule<>(MapActivity.class);
    @Test
    public void onLoginSuccess() {
        onView(withId(R.id.username)).perform(typeText("test_login"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test_login"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.signinbtn)).perform(click());
        onView(withId(R.id.wrong)).check(doesNotExist());
        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
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