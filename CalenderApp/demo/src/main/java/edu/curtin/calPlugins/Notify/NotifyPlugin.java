package edu.curtin.calPlugins.Notify;

import edu.curtin.calenderApp.CalenderEvent;

public class NotifyPlugin {
    private String text;

    public NotifyPlugin(String text) {
        this.text = text;
    }

    public void notifyUser(CalenderEvent event) {
        if(event.getTitle().contains(text)) {
            System.out.println("Event: " + event.getTitle());
            System.out.println("Start Date: " + event.getStartDate());
            System.out.println("Start Time: " + event.getStartTime());
            System.out.println("Duration: " + event.getDuration());
            
        }
    }
}




