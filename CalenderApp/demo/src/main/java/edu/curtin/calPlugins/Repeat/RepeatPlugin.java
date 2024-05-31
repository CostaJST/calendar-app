package edu.curtin.calPlugins.Repeat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.curtin.calenderApp.CalenderEvent;

public class RepeatPlugin {

    /**
     * @param title
     * @param startDate
     * @param startTime
     * @param duration
     */
    public static void RepeatEvents(String title, String startDate, String startTime, long duration) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        try {
            Date initialStartDate = dateFormat.parse(startDate);

            CalenderEvent initialEvent = new CalenderEvent(title, initialStartDate, startTime, duration);
            initialEvent.displayEvent();

            Date currentDate = initialStartDate;
            Date endDate = new Date(initialStartDate.getTime() + 365L * 24 * 60 * 60 * 1000);

            while(currentDate.before(endDate)) {
                int repeat;
                Date nextDate;
                
                nextDate = new Date(currentDate.getTime() + repeat * 24 * 60 * 60 * 1000);
                CalenderEvent repeatEvent = new CalenderEvent(title, nextDate, startTime, duration);
                repeatEvent.displayEvent();
                currentDate = nextDate;
            } 
            
        } catch(ParseException e) {
                System.err.println("Error parsing date: " + e.getMessage());
            }
    }  
}