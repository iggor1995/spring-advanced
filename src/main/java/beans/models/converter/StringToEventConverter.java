package beans.models.converter;

import beans.models.Event;
import beans.services.api.EventService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Resource;

/**
 * Created by Igor_Lapin on 4/29/2018.
 */
public class StringToEventConverter implements Converter<String, Event> {

    @Resource
    @Qualifier("eventServiceImpl")
    EventService eventService;

    @Override
    public Event convert(String eventId) {
        return eventService.getById(Long.valueOf(eventId));
    }
}
