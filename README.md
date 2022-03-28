# USCRecApp_Team28
This is USCRecApp for Team28 README doc

To Run the App: 
    Switch the configuration to "app" (the one with a green Android icon), then click the green arrow "run 'app'" or press ^R
    (Run directly, do not need to build first)
    Or can also run from the "MainActivity" directly

About the Emulator: (minSdk 21, targetSdk 32)
    All emulator with the accepted Sdk should work.
    But since we used Pixel 4 API 30 for testing, we suggest using this emulator.
  
If Emulator connect to WIFI while the computer is connected: Cold Boot the Emulator
    1. Tools -> Device Manager
    2. Virtual Device -> right most drop-down bar
    3. Click "Cold Boot Now"

There are five existing users in our database currently for the Grader to test our App.
    User 1. username: tigojian, password: 12345678
    User 2. username: xutianch, password: 12345678
    User 3. username: alvinshe, password: 12345678
    User 4. username: user1, password: 12345678
    User 5. username: user2, password: 12345678

To Login: 
    Enter the username and password, then click Login OR press enter on keyboard
  
Map Page (Activity): 
    Click the upper left Profile Icon / View Profile to view profile
    Click on the red buttons on the map to go to the booking page of the corresponding recreation center
    Click on the window on the bottom of the page to view booking summary (Note: can slide the window to view different upcoming timeslots)

Booking Page (Activity): 
    Click the upper left Back to go back the the Map Page
    Click the upper right Refresh to refresh the current page (when you stay in the page for a long time and did nothing)
    Click the three button to select the date to make a reservation (can only select the next three days)
    Click Book or Join Waitlist to book a timeslot or join the waitlsit as required (NOTE: a user can book at most one timeslot at a center a day)
  
Summary Page (Activity):
    Click the upper left Back to go back the the Map Page
    The first part shows the upcoming bookings, while the second part shows the history bookings, each window allows sliding to view more
    Click Cancel (for upcoming bookings only) to cancel an upcoming meeting

Profile Page (Activity):
    Click the upper left Back to go back the the Map Page
    Click the upper right Logout to logout this account (will then be redirected to Login Page)

To Test Notifications & Waitlist (need at least two devices):
    1. Since we set capacity=2 for all timeslots (convenient for testing), need to use two account to reserve the same timeslot
    2. Now use a third account to view the same timeslot, the button should now show "join waitlist" with available spot = 0
    3. Use this third account to join waitlist
    4. Use another device to login to one of the previous account (which booked this timeslot before)
    5. Click the small summary window at the bottom of the Map Page to go into the Summary Page
    6. Cancel the corresponding timeslot
    7. Now, the device with the third account logged in should receive the notification (need internet connection)
