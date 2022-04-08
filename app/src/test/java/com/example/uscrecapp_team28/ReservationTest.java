package com.example.uscrecapp_team28;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class ReservationTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUnique_userid() {
    }

    @Test
    public void setUnique_userid() {
    }

    //display all reservation information of a user. make sure the past and future information are correct
    @Test
    public void display_all_reservation_info() {
        //test all reservation information for a user
        Reservation reservation = new Reservation();
        reservation.setUnique_userid("5");
        HashMap<String, ArrayList<BookingItem>>  m = reservation.display_all_reservation_info();
        System.out.println(m);
        assertEquals(1,m.get("future").size());
        assertEquals(2,m.get("history").size());
        assertEquals("106",m.get("future").get(0).getmReservation_id());
        //contain reservation id 104 and 105
        boolean contain104 = false;
        boolean contain105 = false;
        for(BookingItem i:m.get("history")){
            if(i.getmReservation_id().equals("104")){
                contain104 = true;
            }else if(i.getmReservation_id().equals("105")){
                contain105 = true;
            }
        }
        assertTrue(contain104);
        assertTrue(contain105);

    }

    @Test
    public void display_all_reservation_info_for_nonexist_user(){
        Reservation reservation = new Reservation();
        reservation.setUnique_userid("-1");
        HashMap<String, ArrayList<BookingItem>>  m = reservation.display_all_reservation_info();
        System.out.println(m);
        assertEquals(0,m.get("future").size());
        assertEquals(0,m.get("history").size());
    }

    //add a reservation and test to cancel the reservation
    @Test
    public void cancel_reservation() {
        
    }
}