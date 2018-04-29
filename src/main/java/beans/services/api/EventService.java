package beans.services.api;

import beans.models.Auditorium;
import beans.models.Event;

import java.time.LocalDateTime;
import java.util.List;


public interface EventService {

    Event getById(Long id);

    Event create(Event event);

    void remove(Event event);

    Event getEvent(String name, Auditorium auditorium, LocalDateTime dateTime);

    List<Event> getByName(String name);

    List<Event> getAll();

    List<Event> getForDateRange(LocalDateTime from, LocalDateTime to);

    List<Event> getNextEvents(LocalDateTime to);

    Event assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date);
}
