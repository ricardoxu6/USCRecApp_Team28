package com.example.uscrecapp_team28;

public class BookingItem {
    private String mReservation_id;
    private String mText1;
    private String mText2;

    public BookingItem(String reservation_id,String text1, String text2) {
        mReservation_id = reservation_id;
        mText1 = text1;
        mText2 = text2;
    }

    public String getmReservationId(){
        return mReservation_id;
    }
    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }
}
