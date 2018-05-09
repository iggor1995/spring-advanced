package beans.services.impl;

import beans.daos.BookingDAO;
import beans.daos.DaoException;
import beans.daos.UserAccountDao;
import beans.models.*;
import beans.services.ServiceException;
import beans.services.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("bookingServiceImpl")
@PropertySource({"classpath:strategies/booking.properties"})
@Transactional
public class BookingServiceImpl implements BookingService {

    private String error;
    private final EventService eventService;
    private final AuditoriumService auditoriumService;
    private final UserService userService;
    private final BookingDAO bookingDAO;
    private final DiscountService discountService;
    final         int               minSeatNumber;
    final         double            vipSeatPriceMultiplier;
    final         double            highRatedPriceMultiplier;
    final         double            defaultRateMultiplier;

    @Autowired
    public BookingServiceImpl(@Qualifier("eventServiceImpl") EventService eventService,
                              @Qualifier("auditoriumServiceImpl") AuditoriumService auditoriumService,
                              @Qualifier("userServiceImpl") UserService userService,
                              @Qualifier("discountServiceImpl") DiscountService discountService,
                              @Qualifier("bookingDAO") BookingDAO bookingDAO,
                              @Value("${min.seat.number}") int minSeatNumber,
                              @Value("${vip.seat.price.multiplier}") double vipSeatPriceMultiplier,
                              @Value("${high.rate.price.multiplier}") double highRatedPriceMultiplier,
                              @Value("${def.rate.price.multiplier}") double defaultRateMultiplier) {
        this.eventService = eventService;
        this.auditoriumService = auditoriumService;
        this.userService = userService;
        this.bookingDAO = bookingDAO;
        this.discountService = discountService;
        this.minSeatNumber = minSeatNumber;
        this.vipSeatPriceMultiplier = vipSeatPriceMultiplier;
        this.highRatedPriceMultiplier = highRatedPriceMultiplier;
        this.defaultRateMultiplier = defaultRateMultiplier;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return bookingDAO.getAllTickets();
    }

    @Override
    public double getTicketPrice(String eventName, String auditoriumName, LocalDateTime dateTime, List<Integer> seats,
                                 User user) throws ServiceException {
        if (Objects.isNull(eventName)) {
            throw new ServiceException("Event name is [null]");
        }
        if (Objects.isNull(seats)) {
            throw new ServiceException("Seats are [null]");
        }
        if (Objects.isNull(user)) {
            throw new ServiceException("User is [null]");
        }
        if (seats.contains(null)) {
            throw new ServiceException("Seats contain [null]");
        }

        final Auditorium auditorium = auditoriumService.getByName(auditoriumName);

        final Event event = eventService.getEvent(eventName, auditorium, dateTime);
        if (Objects.isNull(event)) {
            throw new IllegalStateException(
                    "There is no event with name: [" + eventName + "] in auditorium: [" + auditorium + "] on date: ["
                    + dateTime + "]");
        }

        final double baseSeatPrice = event.getBasePrice();
        final double rateMultiplier = event.getRate() == Rate.HIGH ? highRatedPriceMultiplier : defaultRateMultiplier;
        final double seatPrice = baseSeatPrice * rateMultiplier;
        final double vipSeatPrice = vipSeatPriceMultiplier * seatPrice;
        final double discount = discountService.getDiscount(user, event);


        validateSeats(seats, auditorium);

        final List<Integer> auditoriumVipSeats = auditorium.getVipSeatsList();
        final List<Integer> vipSeats = auditoriumVipSeats.stream().filter(seats:: contains).collect(
                Collectors.toList());
        final List<Integer> simpleSeats = seats.stream().filter(seat -> !vipSeats.contains(seat)).collect(
                Collectors.toList());

        final double simpleSeatsPrice = simpleSeats.size() * seatPrice;
        final double vipSeatsPrice = vipSeats.size() * vipSeatPrice;

        //        System.out.println("auditoriumVipSeats = " + auditoriumVipSeats);
        //        System.out.println("baseSeatPrice = " + baseSeatPrice);
        //        System.out.println("rateMultiplier = " + rateMultiplier);
        //        System.out.println("vipSeatPriceMultiplier = " + vipSeatPriceMultiplier);
        //        System.out.println("seatPrice = " + seatPrice);
        //        System.out.println("vipSeatPrice = " + vipSeatPrice);
        //        System.out.println("discount = " + discount);
        //        System.out.println("seats = " + seats);
        //        System.out.println("simpleSeats.size() = " + simpleSeats.size());
        //        System.out.println("vipSeats.size() = " + vipSeats.size());

        final double totalPrice = simpleSeatsPrice + vipSeatsPrice;

        return (1.0 - discount) * totalPrice;
    }

    private void validateSeats(List<Integer> seats, Auditorium auditorium) {
        final int seatsNumber = auditorium.getSeatsNumber();
        final Optional<Integer> incorrectSeat = seats.stream().filter(
                seat -> seat < minSeatNumber || seat > seatsNumber).findFirst();
        incorrectSeat.ifPresent(seat -> {
            try {
                throw new ServiceException(
                        String.format("Seat: [%s] is incorrect. Auditorium: [%s] has [%s] seats", seat, auditorium.getName(),
                                      seatsNumber));
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public Ticket bookTicket(User user, Ticket ticket) throws ServiceException, DaoException {
            if(validate(user, ticket)){
                userService.updateUserAccount(new UserAccount(user.getId(),
                        userService.getUserCash(user) - ticket.getPrice()));
                bookingDAO.create(user, ticket);
            }
            else throw new ServiceException(error);
        return ticket;
    }

    private boolean areSeatsAlreadyBooked(Ticket ticket){
        List<Ticket> bookedTickets = bookingDAO.getTickets(ticket.getEvent());
        return bookedTickets.stream().filter(bookedTicket -> ticket.getSeatsList().stream().filter(
                bookedTicket.getSeatsList() :: contains).findAny().isPresent()).findAny().isPresent();
    }

    private boolean hasEnoughCash(User user, Ticket ticket){
        return userService.getUserCash(user) >= ticket.getPrice();
    }

    private boolean validate(User user, Ticket ticket) throws ServiceException {
        if (Objects.isNull(user)) {
            throw new ServiceException("User is [null]");
        }
        User foundUser = userService.getById(user.getId());
        if (Objects.isNull(foundUser)) {
            throw new ServiceException("User: [" + user + "] is not registered");
        }
        if(areSeatsAlreadyBooked(ticket)) {
            error = "seats are already booked";
            return false;
        }
        if(!hasEnoughCash(user, ticket)){
            error = "not enough cash";
            return false;
        }
        return true;
    }

    @Override
    public List<Ticket> getTicketsForEvent(String event, String auditoriumName, LocalDateTime date) {
        final Auditorium auditorium = auditoriumService.getByName(auditoriumName);
        final Event foundEvent = eventService.getEvent(event, auditorium, date);
        return bookingDAO.getTickets(foundEvent);
    }
}
