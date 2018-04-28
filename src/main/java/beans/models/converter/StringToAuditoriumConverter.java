package beans.models.converter;

import beans.models.Auditorium;
import beans.services.api.AuditoriumService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Resource;

/**
 * Created by Igor_Lapin on 4/28/2018.
 */
public class StringToAuditoriumConverter implements Converter<String, Auditorium> {

    @Resource
    @Qualifier("auditoriumServiceImpl")
    AuditoriumService auditoriumService;

    @Override
    public Auditorium convert(String auditoriumName) {
        return auditoriumService.getByName(auditoriumName);
    }
}
