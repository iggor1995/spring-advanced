package beans.services.api;

import beans.models.Auditorium;
import beans.models.Event;
import beans.models.Ticket;

import java.util.List;

public interface AuditoriumService {

    List<Auditorium> getAuditoriums();

    Auditorium getByName(String name);

    int getSeatsNumber(String auditoriumName);

    List<Integer> getVipSeats(String auditoriumName);

    List<Integer> getAvailableSeats(String auditoriumName, Event event, List<Ticket> cartTickets);
}
