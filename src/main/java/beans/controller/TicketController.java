package beans.controller;

import beans.daos.DaoException;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.services.ServiceException;
import beans.services.api.AuditoriumService;
import beans.services.api.BookingService;
import beans.services.api.EventService;
import beans.services.api.UserService;
import beans.services.impl.AuditoriumServiceImpl;
import beans.services.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor_Lapin on 4/28/2018.
 */
@Controller
public class TicketController {

    @Resource
    @Qualifier("bookingServiceImpl")
    BookingService bookingService;

    @Resource
    @Qualifier("userServiceImpl")
    UserService userService;

    @Resource
    @Qualifier("eventServiceImpl")
    EventService eventService;

    @Resource
    @Qualifier("auditoriumServiceImpl")
    AuditoriumService auditoriumService;

    @RequestMapping(value = "/manager/getTickets", method = RequestMethod.POST)
    public String getTickets(@RequestParam("eventName") String event, @RequestParam("auditorium") String auditorium,
                             @RequestParam("date")LocalDateTime dateTime, @ModelAttribute("model")Model model){
        List<Ticket> tickets = bookingService.getTicketsForEvent(event, auditorium, dateTime);
        model.addAttribute("tickets", tickets);
        return "redirect:/home";
    }

    @RequestMapping(value = "/user/bookTicketsPage", method = RequestMethod.POST)
    public String bookTickets(@RequestParam("eventId") String eventId, Model model){

        Event event = eventService.getById(Long.valueOf(eventId));
        Integer seats = auditoriumService.getSeatsNumber(event.getAuditorium().getName());
        model.addAttribute("event", event);
        model.addAttribute("seats", seats);
        return "bookTickets";
    }
    @RequestMapping(value = "/user/bookTickets", method = RequestMethod.POST)
    public String createAndBookTicket(@RequestParam("eventId") String eventId, @RequestParam("userEmail") String userEmail,
                                      @RequestParam("seat") Integer seat, @ModelAttribute("model") ModelMap model) throws ServiceException, DaoException {
        Event event = eventService.getById(Long.valueOf(eventId));
        User user = userService.getUserByEmail(userEmail);
        List<Integer> seatsList = new ArrayList<>();
        seatsList.add(seat);
        Ticket ticket = new Ticket(event, event.getDateTime(), seatsList, user, event.getBasePrice());
        try {
            bookingService.bookTicket(user, ticket);
        } catch (ServiceException e){
            model.addAttribute("error", "seat is already booked");
            return "home";
        }
        return "redirect:/home";
    }
}
