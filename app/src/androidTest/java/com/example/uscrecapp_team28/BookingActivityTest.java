package com.example.uscrecapp_team28;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.*;

import android.app.Activity;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BookingActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);
    private static Matcher<View> getElementFromMatchAtPosition(final Matcher<View> matcher, final int position) {
        return new BaseMatcher<View>() {
            int counter = 0;
            @Override
            public boolean matches(final Object item) {
                if (matcher.matches(item)) {
                    if(counter == position) {
                        counter++;
                        return true;
                    }
                    counter++;
                }
                return false;
            }
            @Override
            public void describeTo(final Description description) {
                description.appendText("Element at hierarchy position "+position);
            }
        };
    }

    // BY SDM
    @Test
    public void makeReservationTest() {
        // login
        onView(withId(R.id.username)).perform(typeText("alvinshe"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.signinbtn)).perform(click());
        // go to booking page (lyon)
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.tomorrowbutton)).perform(click());
        // make reservation
        onView(allOf(getElementFromMatchAtPosition(allOf(withId(R.id.BookButton)), 0), isDisplayed())).perform(click());
        onView(withText("CONFIRM")).inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withText("OK")).inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        // back to map page
        onView(withId(R.id.booking_back)).perform(click());
        // go to summary page
        onView(withId(R.id.summarybtn)).perform(click());
        // cancel reservation
        onView(allOf(getElementFromMatchAtPosition(allOf(withId(R.id.CancelButton)), 0), isDisplayed())).perform(click());
        onView(withText("CONFIRM")).inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        // back to map page
        onView(withId(R.id.returnButton)).perform(click());
        // logout
        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.profile_logout)).perform(click());
        onView(withId(R.id.signin)).check(matches(withText("Sign in")));
    }

//    @Test
//    public void onClickRefresh() {
//        onView(withId(R.id.username)).perform(typeText("bookingtest"), ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.password)).perform(typeText("test"), ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.signinbtn)).perform(click());
//        onView(withId(R.id.wrong)).check(doesNotExist());
////        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
//        onView(withId(R.id.button1)).perform(click());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        String today = sdf.format(cal.getTime());
//        cal.add(Calendar.DATE, 1);
//        String tomorrow =  sdf.format(cal.getTime());
//        cal.add(Calendar.DATE, 1);
//        String third =  sdf.format(cal.getTime());
//        onView(withId(R.id.todaybutton))
//                .check(matches(withText(today)));
//        onView(withId(R.id.tomorrowbutton))
//                .check(matches(withText(tomorrow)));
//        onView(withId(R.id.thirdbutton))
//                .check(matches(withText(third)));
//        onView(withId(R.id.refreshbtn)).perform(click());
//        onView(withId(R.id.booking_back)).perform(click());
//        onView(withId(R.id.profileButton)).perform(click());
//        onView(withId(R.id.profile_logout)).perform(click());
//    }
//
//    @Test
//    public void onClickMap() {
//        onView(withId(R.id.username)).perform(typeText("bookingtest"), ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.password)).perform(typeText("test"), ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.signinbtn)).perform(click());
//        onView(withId(R.id.wrong)).check(doesNotExist());
////        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
//        onView(withId(R.id.button1)).perform(click());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        String today = sdf.format(cal.getTime());
//        cal.add(Calendar.DATE, 1);
//        String tomorrow =  sdf.format(cal.getTime());
//        cal.add(Calendar.DATE, 1);
//        String third =  sdf.format(cal.getTime());
//        onView(withId(R.id.todaybutton))
//                .check(matches(withText(today)));
//        onView(withId(R.id.tomorrowbutton))
//                .check(matches(withText(tomorrow)));
//        onView(withId(R.id.thirdbutton))
//                .check(matches(withText(third)));
//        onView(withId(R.id.booking_back)).perform(click());
//        onView(withId(R.id.profileButton)).perform(click());
//        onView(withId(R.id.profile_logout)).perform(click());
//
//    }
////
//    @Test
//    public void onClickToday() {
//        onView(withId(R.id.username)).perform(typeText("bookingtest"), ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.password)).perform(typeText("test"), ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.signinbtn)).perform(click());
//        onView(withId(R.id.wrong)).check(doesNotExist());
////        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
//        onView(withId(R.id.button1)).perform(click());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        String today = sdf.format(cal.getTime());
//        cal.add(Calendar.DATE, 1);
//        String tomorrow =  sdf.format(cal.getTime());
//        cal.add(Calendar.DATE, 1);
//        String third =  sdf.format(cal.getTime());
//        onView(withId(R.id.todaybutton))
//                .check(matches(withText(today)));
//        onView(withId(R.id.tomorrowbutton))
//                .check(matches(withText(tomorrow)));
//        onView(withId(R.id.thirdbutton))
//                .check(matches(withText(third)));
//        onView(withId(R.id.todaybutton)).perform(click());
//        onView(withId(R.id.booking_back)).perform(click());
//        onView(withId(R.id.profileButton)).perform(click());
//        onView(withId(R.id.profile_logout)).perform(click());
//
//        //onView(withId(R.id.TrecyclerView)).perform(click());
//
//
//    }
//
//    @Test
//    public void onClickSecond() {
//        onView(withId(R.id.username)).perform(typeText("bookingtest"), ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.password)).perform(typeText("test"), ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.signinbtn)).perform(click());
//        onView(withId(R.id.wrong)).check(doesNotExist());
////        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
//        onView(withId(R.id.button1)).perform(click());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        String today = sdf.format(cal.getTime());
//        cal.add(Calendar.DATE, 1);
//        String tomorrow =  sdf.format(cal.getTime());
//        cal.add(Calendar.DATE, 1);
//        String third =  sdf.format(cal.getTime());
//        onView(withId(R.id.todaybutton))
//                .check(matches(withText(today)));
//        onView(withId(R.id.tomorrowbutton))
//                .check(matches(withText(tomorrow)));
//        onView(withId(R.id.thirdbutton))
//                .check(matches(withText(third)));
//        onView(withId(R.id.tomorrowbutton)).perform(click());
//        RecyclerView rv = getCurrentActivity().findViewById(R.id.TrecyclerView);
//        BookingAdapter ba = (BookingAdapter) rv.getAdapter() ;
//        ArrayList<TimeslotItem> tlist = ba.gettSlotList();
//        assertEquals(6, tlist.size());
//        assertEquals(tomorrow, tlist.get(0).getDate());
//        assertEquals("08:00", tlist.get(0).getStart());
//        onView(withId(R.id.booking_back)).perform(click());
//        onView(withId(R.id.profileButton)).perform(click());
//        onView(withId(R.id.profile_logout)).perform(click());
//
//
//    }
//
//    @Test
//    public void onClickThird() {
//        onView(withId(R.id.username)).perform(typeText("bookingtest"), ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.password)).perform(typeText("test"), ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.signinbtn)).perform(click());
//        onView(withId(R.id.wrong)).check(doesNotExist());
////        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
//        onView(withId(R.id.button1)).perform(click());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        String today = sdf.format(cal.getTime());
//        cal.add(Calendar.DATE, 1);
//        String tomorrow =  sdf.format(cal.getTime());
//        cal.add(Calendar.DATE, 1);
//        String third =  sdf.format(cal.getTime());
//        onView(withId(R.id.todaybutton))
//                .check(matches(withText(today)));
//        onView(withId(R.id.tomorrowbutton))
//                .check(matches(withText(tomorrow)));
//        onView(withId(R.id.thirdbutton))
//                .check(matches(withText(third)));
//        onView(withId(R.id.thirdbutton)).perform(click());
//        RecyclerView rv = getCurrentActivity().findViewById(R.id.TrecyclerView);
//        BookingAdapter ba = (BookingAdapter) rv.getAdapter() ;
//        ArrayList<TimeslotItem> tlist = ba.gettSlotList();
//        assertEquals(6, tlist.size());
//        assertEquals(third, tlist.get(0).getDate());
//        assertEquals("08:00", tlist.get(0).getStart());
//        onView(withId(R.id.booking_back)).perform(click());
//        onView(withId(R.id.profileButton)).perform(click());
//        onView(withId(R.id.profile_logout)).perform(click());
//    }
}