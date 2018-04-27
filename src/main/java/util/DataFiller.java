package util;

import beans.models.*;
import beans.services.api.*;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Igor_Lapin on 4/27/2018.
 */
public class DataFiller {

    @Resource
    @Qualifier("userServiceImpl")
    static UserService userService;
    @Resource
    @Qualifier("userServiceImpl")
    static AuditoriumService auditoriumService;
    @Resource
    @Qualifier("userServiceImpl")
    static EventService eventService;

    public static void fillData(){

         String email = "dmitriy.vbabichev@gmail.com";
         String name = "Dmytro Babichev";
         String eventName = "The revenant";
         String auditoriumName = "Blue hall";
         Auditorium blueHall = auditoriumService.getByName(auditoriumName);
         Auditorium yellowHall = auditoriumService.getByName("Yellow hall");
         Auditorium redHall = auditoriumService.getByName("Red hall");
         LocalDateTime dateOfEvent = LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(15, 45, 0));

         userService.register(new User(email, name, LocalDate.now(), "asd"));
         userService.register(new User("laory@yandex.ru", name, LocalDate.of(1992, 4, 29), "asd"));

         User userByEmail = userService.getUserByEmail(email);
         System.out.println("User with email: [" + email + "] is " + userByEmail);
         System.out.println();

         System.out.println("All users with name: [" + name + "] are: ");
         userService.getUsersByName(name).forEach(System.out:: println);
         System.out.println();

        Event event1 = eventService.create(
                new Event(eventName, Rate.HIGH, 60, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(9, 0, 0)),
                        blueHall));
    }
}
