package com.example.domain;

import java.sql.Date;

/**
 * Created by Administrator on 2017-03-08.
 */
public class Booking {
    private int BookingID;
    private Date StartTime;
    private Date EndTime;
    private int Room_ID;
    private int User_ID;

    public Booking(int bookingID, Date startTime, Date endTime, int room_ID, int user_ID) {
        BookingID = bookingID;
        StartTime = startTime;
        EndTime = endTime;
        Room_ID = room_ID;
        User_ID = user_ID;
    }

    public int getBookingID() {
        return BookingID;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public int getRoom_ID() {
        return Room_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }
}
