package edu.curtin.API;

import java.util.Date;

public interface CalenderAPI {
    
    //Add an event to the calender
    void addEvent(String title, Date startDate, String startTime, long duration);

    //Register a callback to be notified when a particular named event starts
    void eventCallback(String title, notifyEvent callback);

    //Interface for event start notification
    @FunctionalInterface
    interface notifyEvent {
        void eventStart(String title, Date startDate, String startTime);
    }

}
    


    

