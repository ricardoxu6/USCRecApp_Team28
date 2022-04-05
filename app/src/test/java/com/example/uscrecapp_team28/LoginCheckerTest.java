package com.example.uscrecapp_team28;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginCheckerTest {

    @Before
    public void setup() {
    }

    @After
    public void cleanup() throws Exception {
    }

    @Test
    public void test_login_success(){
        LoginChecker loginChecker = new LoginChecker("test","test","deviceid2");
        loginChecker.check_login();
        assertTrue(loginChecker.getLoginFlag());
    }

    public void testGetUsername() {
    }

    public void testSetUsername() {
    }

    public void testGetPassword() {
    }

    public void testSetPassword() {
    }

    public void testGetLoginFlag() {
    }

    public void testSetLoginFlag() {
    }

    public void testCheck_login() {
    }
}