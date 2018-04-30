package beans.daos.mocks;

import beans.daos.DaoException;
import beans.daos.db.EventDAOImpl;
import beans.models.Event;

import java.util.List;

public class EventDAOMock extends EventDAOImpl {

    private final List<Event> events;

    public EventDAOMock(List<Event> events) {
        this.events = events;
    }

    public void init() {
        cleanup();
        events.forEach((event) -> {
            try {
                create(event);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        });
    }

    public void cleanup() {
        System.out.println("deleting ");
        System.out.println(getAll());
        getAll().forEach(this :: delete);
    }
}
