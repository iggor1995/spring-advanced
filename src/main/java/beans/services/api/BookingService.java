package beans.services.api;

import beans.daos.DaoException;
import beans.models.Ticket;
import beans.models.User;
import beans.services.ServiceException;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    List<Ticket> getAllTickets();

    double getTicketPrice(String event, String auditorium, LocalDateTime dateTime, List<Integer> seats, User user) throws ServiceException;

    Ticket bookTicket(User user, Ticket ticket) throws ServiceException, DaoException;

    List<Ticket> getTicketsForEvent(String event, String auditorium, LocalDateTime date);

    Ticket getTicketById(long id);

    List<Ticket> getUserTickets(User user) throws ServiceException;
}
