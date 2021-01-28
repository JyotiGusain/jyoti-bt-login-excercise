package uk.co.ee.loginexercise.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static uk.co.ee.loginexercise.service.MessageService.getGreetingMessage;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @Test
    public void testGreetingMessage() {
        //given
        //to test morning
        LocalDateTime morningTime = LocalDateTime.parse("2020-12-05T07:15:30");
        //when
        String actualMessage = getGreetingMessage(morningTime);
        //then
        assertEquals("Good Morning ", actualMessage);


        //to test morning
        LocalDateTime afterTime = LocalDateTime.parse("2020-12-05T14:15:30");
        //when
        String actualMessage1 = getGreetingMessage(afterTime);
        //then
        assertEquals("Good Afternoon ", actualMessage1);

        //to test morning
        LocalDateTime eveningTime = LocalDateTime.parse("2020-12-05T21:15:30");
        //when
        String actualMessage2 = getGreetingMessage(eveningTime);
        //then
        assertEquals("Good Evening ", actualMessage2);

        //to test morning
        LocalDateTime nightTime = LocalDateTime.parse("2020-12-05T23:15:30");
        //when
        String actualMessage3 = getGreetingMessage(nightTime);
        //then
        assertEquals("Good Night ", actualMessage3);
    }

}