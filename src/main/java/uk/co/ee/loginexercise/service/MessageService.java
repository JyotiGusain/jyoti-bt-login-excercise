package uk.co.ee.loginexercise.service;

import java.time.LocalDateTime;


public class MessageService {

    public static String getGreetingMessage(LocalDateTime localDateTime) {
        String message = null;
        int hour = localDateTime.getHour();
        if (hour > 06 && hour <= 12) {
            message = "Good Morning ";
        } else if (hour > 12 && hour <= 18) {
            message = "Good Afternoon ";
        } else if (hour > 18 && hour <= 22) {
            message = "Good Evening ";
        } else if ((hour > 22 || hour <= 24 || hour <= 06)) {
            message = "Good Night ";
        }
        return message;
    }
}
