package beans.models.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * Created by Igor_Lapin on 4/28/2018.
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String date) {
        return LocalDate.parse(date);
    }
}
