package client.deserializer;

import client.model.Auditorium;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * Created by Igor_Lapin on 4/29/2018.
 */
public class AuditoriumDeserializer extends StdDeserializer<Auditorium> {

    protected AuditoriumDeserializer() {
        super(Auditorium.class);
    }

    @Override
    public Auditorium deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Auditorium auditorium = jsonParser.readValueAs(Auditorium.class);
        return auditorium;
    }
}
