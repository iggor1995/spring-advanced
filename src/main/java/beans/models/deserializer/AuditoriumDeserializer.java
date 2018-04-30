package beans.models.deserializer;

import beans.daos.db.AuditoriumDAOImpl;
import beans.models.Auditorium;
import beans.services.api.AuditoriumService;
import beans.services.impl.AuditoriumServiceImpl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Igor_Lapin on 4/29/2018.
 */
public class AuditoriumDeserializer extends StdDeserializer<Auditorium> {

    protected AuditoriumDeserializer() {
        super(Auditorium.class);
    }

    @Override
    public Auditorium deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Integer auditoriumId = jsonParser.readValueAs(Integer.class);
        Auditorium auditorium = new Auditorium();
        auditorium.setId(auditoriumId);
        return auditorium;
    }
}
