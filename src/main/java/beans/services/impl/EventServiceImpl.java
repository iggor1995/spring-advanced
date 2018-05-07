package beans.services.impl;

import beans.daos.DaoException;
import beans.daos.EventDAO;
import beans.models.Auditorium;
import beans.models.Event;
import beans.services.api.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("eventServiceImpl")
@Transactional
public class EventServiceImpl implements EventService {

    private final EventDAO eventDAO;

    @Autowired
    public EventServiceImpl(@Qualifier("eventDAO") EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public Event create(Event event) throws DaoException {

        return eventDAO.create(event);
    }

    public void remove(Event event) throws DaoException {
        eventDAO.delete(event);
    }

    public Event getById(Long id){
        return eventDAO.getById(id);
    }

    public List<Event> getByName(String name) {
        return eventDAO.getByName(name);
    }

    public Event getEvent(String name, Auditorium auditorium, LocalDateTime dateTime) {
        return eventDAO.get(name, auditorium, dateTime);
    }

    public List<Event> getAll() {
        return eventDAO.getAll();
    }

    public List<Event> getForDateRange(LocalDateTime from, LocalDateTime to) {
        return eventDAO.getForDateRange(from, to);
    }

    public List<Event> getNextEvents(LocalDateTime to) {
        return eventDAO.getNext(to);
    }

    public Event assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date) throws DaoException {
        final Event updatedEvent = new Event(event.getId(), event.getName(), event.getRate(), event.getBasePrice(), date, auditorium);
        return eventDAO.update(updatedEvent);
    }
}
