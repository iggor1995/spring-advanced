package beans.daos;

import beans.models.Auditorium;
import beans.models.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public interface EventDAO {

    Event getById(Long id);

    Event create(Event event) throws DaoException;

    Event update(Event event) throws DaoException;

    Event get(String eventName, Auditorium auditoriumName, LocalDateTime dateTime);

    void delete(Event event) throws DaoException;

    List<Event> getByName(String name);

    List<Event> getByNameAndDate(String name, LocalDateTime dateTime);

    List<Event> getAll();

    List<Event> getForDateRange(LocalDateTime from, LocalDateTime to);

    List<Event> getNext(LocalDateTime to);

    List<Event> getByAuditoriumAndDate(Auditorium auditorium, LocalDateTime date);

    static void validateEvent(Event event) throws DaoException {
        if (Objects.isNull(event)) {
            throw new DaoException("Event is [null]");
        }
        if (Objects.isNull(event.getName())) {
            throw new DaoException("Event's name is [null]. Event: [" + event + "]");
        }
        if (Objects.isNull(event.getRate())) {
            throw new DaoException("Events's rate is [null]. Event: [" + event + "]");
        }
    }
}
