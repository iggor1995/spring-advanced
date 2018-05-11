package beans.controller;

import beans.daos.DaoException;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.models.UserAccount;
import beans.services.ServiceException;
import beans.services.api.*;
import beans.services.discount.DiscountStrategy;
import beans.services.impl.AuditoriumServiceImpl;
import beans.services.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
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

    @Resource
    @Qualifier("birthdayStrategy")
    DiscountStrategy birthdayStrategy;

    @Resource
    @Qualifier("ticketsStrategy")
    DiscountStrategy ticketsStrategy;

    @RequestMapping(value = "/manager/getTickets", method = RequestMethod.POST)
    public String getTickets(@RequestParam("eventName") String event, @RequestParam("auditorium") String auditorium,
                             @RequestParam("date")LocalDateTime dateTime, @ModelAttribute("model")Model model){
        List<Ticket> tickets = bookingService.getTicketsForEvent(event, auditorium, dateTime);
        model.addAttribute("tickets", tickets);
        return "redirect:/home";
    }

    @RequestMapping(value = "/user/bookTicketsPage", method = RequestMethod.POST)
    @SuppressWarnings("unchecked")
    public String bookTickets(@RequestParam("eventId") String eventId, Model model, HttpServletRequest request){

        Event event = eventService.getById(Long.valueOf(eventId));
        List<Ticket> tickets = (List<Ticket>) request.getSession().getAttribute("cart");
        List<Integer> seats = auditoriumService.getAvailableSeats(event.getAuditorium().getName(), event, tickets);
        model.addAttribute("event", event);
        model.addAttribute("seats", seats);
        return "bookTickets";
    }

    @RequestMapping(value = "/user/bookTickets", method = RequestMethod.POST)
    @SuppressWarnings("unchecked")
    public String addTicketToCart(@RequestParam("eventId") String eventId, @RequestParam("userEmail") String userEmail,
                                      @RequestParam("seat") Integer seat, @ModelAttribute("model") ModelMap model, HttpServletRequest request) throws ServiceException, DaoException {
        Event event = eventService.getById(Long.valueOf(eventId));
        User user = userService.getUserByEmail(userEmail);
        List<Integer> seatsList = new ArrayList<>();
        seatsList.add(seat);
        Ticket ticket = new Ticket(event, event.getDateTime(), seatsList, user, event.getBasePrice());
        List<Ticket> cart = (List<Ticket>) request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new ArrayList<>();
        }
        cart.add(ticket);
        request.getSession().setAttribute("cart", cart);
        return "redirect:/home";
    }

    @RequestMapping(value = "/user/bookCart", method = RequestMethod.POST)
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor = ServiceException.class )
    public String bookCart(@RequestParam("userEmail") String userEmail,
                                       @ModelAttribute("model") ModelMap model, HttpServletRequest request) throws ServiceException, DaoException {
        User user = userService.getUserByEmail(userEmail);
        List<Ticket> cart = (List<Ticket>) request.getSession().getAttribute("cart");
        if(cart != null) {
            double discount = ticketsStrategy.calculateDiscount(user) + birthdayStrategy.calculateDiscount(user);
            try {
                for (Ticket ticket : cart) {
                    ticket.setPrice(ticket.getPrice() - ticket.getPrice() * discount);
                    bookingService.bookTicket(user, ticket);
                }
            } catch (ServiceException e) {
                model.addAttribute("error", e);
                return "cart";
            }
            request.getSession().removeAttribute("cart");
            UserAccount userAccount = userService.getUserAccount(user.getId());
            userAccount.setCash(userAccount.getCash() + discount);
            userService.updateUserAccount(userAccount);
            model.addAttribute("discount", discount);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/user/getCart", method = RequestMethod.GET)
    @SuppressWarnings("unchecked")
    public String getCart(@ModelAttribute("model") ModelMap model, HttpServletRequest request, Principal principal){
        List<Ticket> tickets = (List<Ticket>) request.getSession().getAttribute("cart");
        double totalprice = 0;
        UserAccount userAccount = userService.getUserAccount(userService.getUserByEmail(principal.getName()).getId());
        model.addAttribute("userCash", userAccount.getCash());
        if(tickets != null)
        for(Ticket ticket : tickets){
            totalprice += ticket.getPrice();
        }
        model.addAttribute("totalPrice", totalprice);
        return "cart";
    }

    @RequestMapping(value = "/user/getTickets", method = RequestMethod.POST)
    @SuppressWarnings("unchecked")
    public String getCart(@ModelAttribute("model") ModelMap model, @RequestParam("userEmail") String userEmail) throws ServiceException {
        List<Ticket> tickets = bookingService.getUserTickets(userService.getUserByEmail(userEmail));
        model.addAttribute("tickets", tickets);
        return "userTickets";
    }

    @RequestMapping(value = "/user/deleteFromCart", method = RequestMethod.POST)
    @SuppressWarnings("unchecked")
    public String removeFromCart(HttpServletRequest request) throws ServiceException {
        request.getSession().removeAttribute("cart");
        return "redirect:/user/getCart";
    }

}
