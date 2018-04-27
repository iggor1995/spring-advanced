package beans.formatter;

import beans.models.Auditorium;
import beans.services.api.AuditoriumService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by Igor_Lapin on 4/27/2018.
 */

@Component
public class AuditoriumFormatter implements Formatter<Auditorium> {

    @Resource
    @Qualifier("auditoriumServiceImpl")
    AuditoriumService auditoriumService;


    @Override
    public Auditorium parse(String auditoriumName, Locale locale) throws ParseException {
        return auditoriumService.getByName(auditoriumName);
    }

    @Override
    public String print(Auditorium auditorium, Locale locale) {
        return auditorium.getName();
    }
}
