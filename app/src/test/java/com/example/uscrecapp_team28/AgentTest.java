package com.example.uscrecapp_team28;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

public class AgentTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testInit_info() {
    }

    public void testLogout() {
    }

    public void testGetUnique_userid() {
    }

    public void testGetUsername() {
    }

    public void testGetPassword() {
    }

    public void testGetUnique_center_id() {
    }

    public void testGetUnique_timeslot_id() {
    }

    public void testGetDevice_id() {
    }

    public void testSetUnique_userid() {
    }

    public void testSetUsername() {
    }

    public void testSetPassword() {
    }

    public void testSetUnique_center_id() {
    }

    public void testSetUnique_timeslot_id() {
    }

    public void testSetDevice_id() {
    }

    public void testGetReal_username() {
    }

    public void testSetReal_username() {
    }

    public void testGetReal_password() {
    }

    public void testSetReal_password() {
    }

    public void testGetUscid() {
    }

    public void testSetUscid() {
    }

    public void testGetPhotourl() {
    }

    public void testSetPhotourl() {
    }

    public void testTestGetName() {
    }

    public void testTestSetName() {
    }

    public void testGetEmail() {
    }

    public void testSetEmail() {
    }

    public void testUser_login() {
    }

    public void testCheck_loggedin() {
    }

    public void testView_profile() {
    }

    public void testView_all_timeslots() {
        Agent ag = new Agent();
        ag.setDevice_id("deviceid");
        ag.init_info();
        ArrayList<TimeslotItem> tsi = new ArrayList<TimeslotItem>();
        tsi = ag.view_all_timeslots("1", "2022-12-12");
        assertEquals(6, tsi.size());
        assertEquals("10:00", tsi.get(1).getStart());
    }

    public void testMake_reservation() {
        Agent ag = new Agent();
        ag.setDevice_id("deviceid");
        ag.init_info();

        int r1 = ag.make_reservation("5","4382", "303", 0);
        int r0 = ag.make_reservation("5","4381", "303", 2);
        int r2 = ag.make_reservation("5","4383", "303", 1);
        assertEquals(1, r1);
        assertEquals(0, r0);
        assertEquals(2, r2);
        try {
            //new MakeBooking(unique_timeslot_id, unique_user_id,date_id, max_capacity).execute().get();


            Thread thread_del = new Thread(new Runnable() {
                @Override
                public void run() {
                    try  {
                        System.out.println("enter background");
                        //connect to sql database
                        Class.forName("com.mysql.jdbc.Driver");
                        String connectionUrl = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3479112?characterEncoding=latin1";
                        Connection connection = DriverManager.getConnection(connectionUrl,"sql3479112","k1Q9Fq3375");
                        Statement s = connection.createStatement();
                        System.out.println("after connection");
                        String query4 = String.format(
                                "DELETE FROM reservation WHERE user_id = %s AND timeslot_id = %s", "5", "4381");
                        String query5 = String.format(
                                "DELETE FROM availability WHERE user_id = %s AND date_id = %s", "5", "303");
                        int result = s.executeUpdate(query5);
                        int result2 = s.executeUpdate(query4);
                        int del = 0;
                        if(result==1 && result2==1){
                            del =1;
                            assertEquals(1, del);
                        }
                        connection.close();

                    } catch (Exception e){
                        System.out.println("Exception");
                        e.printStackTrace();
                    }
                }
            });
            thread_del.start();
            thread_del.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void testCancel_reservation() {
    }

    public void testJoin_waitlist() {
        Agent ag = new Agent();
        ag.setDevice_id("deviceid");
        ag.init_info();
        int r1 = ag.join_waitlist("5", "4384");
        int r2 = ag.join_waitlist("5", "4385");
        int r0 = ag.join_waitlist("5", "4386");
        assertEquals(1, r1);
        assertEquals(2, r2);
        assertEquals(0, r0);
        try {
            Thread thread_del_join = new Thread(new Runnable() {
                @Override
                public void run() {
                    try  {
                        System.out.println("enter background");
                        //connect to sql database
                        Class.forName("com.mysql.jdbc.Driver");
                        String connectionUrl = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3479112?characterEncoding=latin1";
                        Connection connection = DriverManager.getConnection(connectionUrl,"sql3479112","k1Q9Fq3375");
                        Statement s = connection.createStatement();
                        System.out.println("after connection");
                        String query4 = String.format(
                                "DELETE FROM waitlist WHERE user_id = %s AND timeslot_id = %s", "5", "4386");
                        int result2 = s.executeUpdate(query4);
                        int del = 0;
                        if(result2==1){
                            del =1;
                            assertEquals(1, del);
                        }
                        connection.close();

                    } catch (Exception e){
                        System.out.println("Exception");
                        e.printStackTrace();
                    }
                }
            });
            thread_del_join.start();
            thread_del_join.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testView_all_reservations() {
    }

    public void testTestToString() {
    }
}