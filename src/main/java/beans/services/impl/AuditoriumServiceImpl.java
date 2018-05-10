package beans.services.impl;

import beans.daos.AuditoriumDAO;
import beans.daos.BookingDAO;
import beans.models.Auditorium;
import beans.models.Event;
import beans.models.Ticket;
import beans.services.api.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("auditoriumServiceImpl")
@Transactional
public class AuditoriumServiceImpl implements AuditoriumService {

    private final AuditoriumDAO auditoriumDAO;

    @Resource
    @Qualifier("bookingDaoImpl")
    BookingDAO bookingDAO;

    @Autowired
    public AuditoriumServiceImpl(@Qualifier("auditoriumDAO") AuditoriumDAO auditoriumDAO) {
        this.auditoriumDAO = auditoriumDAO;
    }

    @Override
    public List<Auditorium> getAuditoriums() {
        return auditoriumDAO.getAll();
    }

    @Override
    public Auditorium getByName(String name) {
        return auditoriumDAO.getByName(name);
    }

    @Override
    public int getSeatsNumber(String auditoriumName) {
        return auditoriumDAO.getByName(auditoriumName).getSeatsNumber();
    }

    @Override
    public List<Integer> getVipSeats(String auditoriumName) {
        return auditoriumDAO.getByName(auditoriumName).getVipSeatsList();
    }

    @Override
    public List<Integer> getAvailableSeats(String auditoriumName, Event event, List<Ticket> cartTickets) {
        List<Ticket> bookedTickets = bookingDAO.getTickets(event);
        List<Integer> bookedSeats = new ArrayList<>();
        if(cartTickets != null)
            bookedTickets.addAll(cartTickets.stream().filter(ticket -> ticket.getEvent().equals(event)).collect(Collectors.toList()));
        List<Integer> availableSeats = new ArrayList<>();
        for(Ticket ticket : bookedTickets){
            String seats[] = ticket.getSeats().split(",");
            for(String seat : seats){
                bookedSeats.add(Integer.valueOf(seat));
            }
        }
        int seatsNumber = getSeatsNumber(auditoriumName);
        for(int i = 1; i <= seatsNumber; i++){
            if(!bookedSeats.contains(i)){
                availableSeats.add(i);
            }
        }
        return availableSeats;
    }
}
