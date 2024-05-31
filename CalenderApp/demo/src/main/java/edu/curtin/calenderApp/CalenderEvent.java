package edu.curtin.calenderApp;

import java.util.Date;
import java.text.SimpleDateFormat;

public class CalenderEvent {
    private String title;
    private Date startDate;
    private String startTime;
    private long duration;

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public long getDuration() {
        return duration;
    }


    public CalenderEvent(String title, Date starDate, String startTime, long duration) {
        this.title = title;
        this.startDate = starDate;
        this.startTime = startTime;
        this.duration = duration;
    }
    

    public void displayEvent() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Title: " + title);
        System.out.println("Start Date: " + dateFormat.format(startDate));
        System.out.println("Start Time " + startTime);
        System.out.println("Duration: " + duration + "milliseconds");
    }
}
