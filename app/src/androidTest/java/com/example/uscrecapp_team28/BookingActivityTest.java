package com.example.uscrecapp_team28;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;
import static org.junit.Assert.*;

import android.app.Activity;

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
    public ActivityTestRule<MainActivity> loginactivityRule =
            new ActivityTestRule<>(MainActivity.class);
//    @Rule
//    public ActivityTestRule<MapActivity> mapactivityRule =
//            new ActivityTestRule<>(MapActivity.class);
//    @Rule
//    public ActivityTestRule<BookingActivity> bookingactivityRule =
//            new ActivityTestRule<>(BookingActivity.class);

//    @Rule
//    public IntentsTestRule<MapActivity> mapintentsTestRule =
//            new IntentsTestRule<>(MapActivity.class);
//    @Rule
//    public IntentsTestRule<BookingActivity> bookingintentsTestRule =
//            new IntentsTestRule<>(BookingActivity.class);

    public Activity getCurrentActivity() {
        final Activity[] currentActivity = new Activity[1];
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                Collection<Activity> allActivities = ActivityLifecycleMonitorRegistry.getInstance()
                        .getActivitiesInStage(Stage.RESUMED);
                if (!allActivities.isEmpty()) {
                    currentActivity[0] = allActivities.iterator().next();
                }
            }
        });
        return currentActivity[0];
    }

    @Test
    public void onClickRefresh() {
        onView(withId(R.id.username)).perform(typeText("bookingtest"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.signinbtn)).perform(click());
        onView(withId(R.id.wrong)).check(doesNotExist());
//        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
        onView(withId(R.id.button1)).perform(click());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String tomorrow =  sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String third =  sdf.format(cal.getTime());
        onView(withId(R.id.todaybutton))
                .check(matches(withText(today)));
        onView(withId(R.id.tomorrowbutton))
                .check(matches(withText(tomorrow)));
        onView(withId(R.id.thirdbutton))
                .check(matches(withText(third)));
        onView(withId(R.id.refreshbtn)).perform(click());
        onView(withId(R.id.booking_back)).perform(click());
        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.profile_logout)).perform(click());


    }

    @Test
    public void onClickMap() {
        onView(withId(R.id.username)).perform(typeText("bookingtest"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.signinbtn)).perform(click());
        onView(withId(R.id.wrong)).check(doesNotExist());
//        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
        onView(withId(R.id.button1)).perform(click());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String tomorrow =  sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String third =  sdf.format(cal.getTime());
        onView(withId(R.id.todaybutton))
                .check(matches(withText(today)));
        onView(withId(R.id.tomorrowbutton))
                .check(matches(withText(tomorrow)));
        onView(withId(R.id.thirdbutton))
                .check(matches(withText(third)));
        onView(withId(R.id.booking_back)).perform(click());
        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.profile_logout)).perform(click());

    }
//
    @Test
    public void onClickToday() {
        onView(withId(R.id.username)).perform(typeText("bookingtest"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.signinbtn)).perform(click());
        onView(withId(R.id.wrong)).check(doesNotExist());
//        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
        onView(withId(R.id.button1)).perform(click());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String tomorrow =  sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String third =  sdf.format(cal.getTime());
        onView(withId(R.id.todaybutton))
                .check(matches(withText(today)));
        onView(withId(R.id.tomorrowbutton))
                .check(matches(withText(tomorrow)));
        onView(withId(R.id.thirdbutton))
                .check(matches(withText(third)));
        onView(withId(R.id.todaybutton)).perform(click());
        onView(withId(R.id.booking_back)).perform(click());
        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.profile_logout)).perform(click());

        //onView(withId(R.id.TrecyclerView)).perform(click());


    }
//
    @Test
    public void onClickSecond() {
        onView(withId(R.id.username)).perform(typeText("bookingtest"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.signinbtn)).perform(click());
        onView(withId(R.id.wrong)).check(doesNotExist());
//        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
        onView(withId(R.id.button1)).perform(click());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String tomorrow =  sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String third =  sdf.format(cal.getTime());
        onView(withId(R.id.todaybutton))
                .check(matches(withText(today)));
        onView(withId(R.id.tomorrowbutton))
                .check(matches(withText(tomorrow)));
        onView(withId(R.id.thirdbutton))
                .check(matches(withText(third)));
        onView(withId(R.id.tomorrowbutton)).perform(click());
        RecyclerView rv = getCurrentActivity().findViewById(R.id.TrecyclerView);
        BookingAdapter ba = (BookingAdapter) rv.getAdapter() ;
        ArrayList<TimeslotItem> tlist = ba.gettSlotList();
        assertEquals(6, tlist.size());
        assertEquals(tomorrow, tlist.get(0).getDate());
        assertEquals("08:00", tlist.get(0).getStart());
        onView(withId(R.id.booking_back)).perform(click());
        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.profile_logout)).perform(click());


    }
//
    @Test
    public void onClickThird() {
        onView(withId(R.id.username)).perform(typeText("bookingtest"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.signinbtn)).perform(click());
        onView(withId(R.id.wrong)).check(doesNotExist());
//        intended(hasComponent("com.example.uscrecapp_team28.MapActivity"));
        onView(withId(R.id.button1)).perform(click());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String tomorrow =  sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String third =  sdf.format(cal.getTime());
        onView(withId(R.id.todaybutton))
                .check(matches(withText(today)));
        onView(withId(R.id.tomorrowbutton))
                .check(matches(withText(tomorrow)));
        onView(withId(R.id.thirdbutton))
                .check(matches(withText(third)));
        onView(withId(R.id.thirdbutton)).perform(click());
        RecyclerView rv = getCurrentActivity().findViewById(R.id.TrecyclerView);
        BookingAdapter ba = (BookingAdapter) rv.getAdapter() ;
        ArrayList<TimeslotItem> tlist = ba.gettSlotList();
        assertEquals(6, tlist.size());
        assertEquals(third, tlist.get(0).getDate());
        assertEquals("08:00", tlist.get(0).getStart());
        onView(withId(R.id.booking_back)).perform(click());
        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.profile_logout)).perform(click());
    }
}