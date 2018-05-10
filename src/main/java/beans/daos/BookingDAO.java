package beans.daos;

import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;

import java.util.List;
import java.util.Objects;


public interface BookingDAO {

    Ticket create(User user, Ticket ticket) throws DaoException;

    void delete(User user, Ticket booking) throws DaoException;

    List<Ticket> getTickets(Event event);

    List<Ticket> getTickets(User user) throws DaoException;

    long countTickets(User user) throws DaoException;

    List<Ticket> getAllTickets();

    Ticket getById(long id);

    static void validateUser(User user) throws DaoException {
        if (Objects.isNull(user)) {
            throw new DaoException("User is [null]");
        }
        if (Objects.isNull(user.getEmail())) {
            throw new DaoException("User email is [null]");
        }
    }

    static void validateTicket(Ticket ticket) throws DaoException {
        if (Objects.isNull(ticket)) {
            throw new DaoException("Ticket is [null]");
        }
    }
}
