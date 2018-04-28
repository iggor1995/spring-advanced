package beans.models.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Igor_Lapin on 4/28/2018.
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String dateString) {
        LocalDateTime dateTimeLocal;
        String[] dateTime = dateString.split("T");
        LocalDate date = LocalDate.parse(dateTime[0]);
        LocalTime time = LocalTime.parse(dateTime[1]);
        dateTimeLocal = LocalDateTime.of(date, time);
        return dateTimeLocal;
    }
}
