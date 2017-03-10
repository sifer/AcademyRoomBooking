package com.example.domain;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * Created by Administrator on 2017-03-08.
 */
public class Booking {
    private int BookingID;
    private Timestamp StartTime;
    private Timestamp EndTime;
    private double differenceMinutes;
    private int dayOfWeek;
    private String stringDayOfWeek;
    private int weekOfYear;
    private double fixedMargin;
    private int Room_ID;
    private int User_ID;

    public Booking(int bookingID, Timestamp startTime, Timestamp endTime, int room_ID, int user_ID) {
        BookingID = bookingID;
        StartTime = startTime;
        EndTime = endTime;
        differenceMinutes = (double) (endTime.getTime()-startTime.getTime())/(60*1000);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startTime.getTime());
        weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        fixedMargin = (cal.get((Calendar.HOUR_OF_DAY))*60-(8*60))+((cal.get(Calendar.MINUTE)));
        Room_ID = room_ID;
        User_ID = user_ID;

        switch(dayOfWeek){
            case 1:
                stringDayOfWeek="Sunday";
                break;
            case 2:
                stringDayOfWeek="Monday";
                break;
            case 3:
                stringDayOfWeek="Tuesday";
                break;
            case 4:
                stringDayOfWeek="Wednesday";
                break;
            case 5:
                stringDayOfWeek="Thursday";
                break;
            case 6:
                stringDayOfWeek="Friday";
                break;
            case 7:
                stringDayOfWeek="Saturday";
                break;
        }

        }


    public int getBookingID() {
        return BookingID;
    }

    public Timestamp getStartTime() {
        return StartTime;
    }

    public Timestamp getEndTime() {
        return EndTime;
    }

    public double getDifferenceMinutes() {
        return differenceMinutes;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getWeekOfYear() {
        return weekOfYear;
    }

    public int getRoom_ID() {
        return Room_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }
    public double getFixedMargin() {
        return fixedMargin;
    }

    public void setFixedMargin(double fixedMargin) {
        this.fixedMargin = fixedMargin;
    }

    public String getStringDayOfWeek() {
        return stringDayOfWeek;
    }

}
