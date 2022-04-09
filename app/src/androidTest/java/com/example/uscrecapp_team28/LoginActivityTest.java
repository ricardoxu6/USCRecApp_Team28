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

import android.content.Intent;

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
//    @Rule
//    public IntentsTestRule<MapActivity> intentsTestRule1 =
//            new IntentsTestRule<>(MapActivity.class);
//    @Rule
//    public IntentsTestRule<LoginActivity> intentsTestRule3 =
//            new IntentsTestRule<>(LoginActivity.class);
//    @Rule
//    public IntentsTestRule<ProfileActivity> intentsTestRule2 =
//            new IntentsTestRule<>(ProfileActivity.class);
    @Test
    public void onLoginSuccess() {
        onView(withId(R.id.username)).perform(typeText("test_login"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test_login"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.signinbtn)).perform(click());
        // already jump to map page, so the wrong field will not exist
//        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
        onView(withId(R.id.wrong)).check(doesNotExist());
        // check if in map page already
        onView(withId(R.id.profileText)).check(matches(withText("VIEW PROFILE")));
        onView(withId(R.id.summarybtn)).check(matches(withText("UPCOMING BOOKINGS")));
        // click into profile to further check if the identity is a match (icon)
        onView(withId(R.id.profileButton)).perform(click());
//        intended(hasComponent("com.example.uscrecapp_team28.ProfileActivity"));
        onView(withId(R.id.profile_name)).check(matches(withText("TESTNAME")));
        onView(withId(R.id.profile_username)).check(matches(withText("Username: test_login")));
        onView(withId(R.id.profile_email)).check(matches(withText("Email: testEMAIL")));
        onView(withId(R.id.profile_uscid)).check(matches(withText("USCid: testUSCID")));
        onView(withId(R.id.profile_back)).perform(click());
//        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
        // click into profile to further check if the identity is a match (text)
        onView(withId(R.id.profileText)).perform(click());
//        intended(hasComponent("com.example.uscrecapp_team28.ProfileActivity"));
        onView(withId(R.id.profile_name)).check(matches(withText("TESTNAME")));
        onView(withId(R.id.profile_username)).check(matches(withText("Username: test_login")));
        onView(withId(R.id.profile_email)).check(matches(withText("Email: testEMAIL")));
        onView(withId(R.id.profile_uscid)).check(matches(withText("USCid: testUSCID")));
        onView(withId(R.id.profile_back)).perform(click());
//        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
    }

    @Test
    public void onLoginFail() {
        onView(withId(R.id.username)).perform(typeText("test_login"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test_login_wrong"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.signinbtn)).perform(click());
        // a wrong message prompt
        onView(withId(R.id.wrong)).check(matches(withText("Wrong username/password. Try again.")));
        // views in map should not exist
        onView(withId(R.id.profileText)).check(doesNotExist());
        onView(withId(R.id.profileButton)).check(doesNotExist());
        onView(withId(R.id.summarybtn)).check(doesNotExist());
        onView(withId(R.id.button1)).check(doesNotExist());
        onView(withId(R.id.button2)).check(doesNotExist());
    }
}