package beans.models.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Igor_Lapin on 4/29/2018.
 */
public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    protected LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String[] dateTime = parser.readValueAs(String.class).split(" ");
        LocalDate date = LocalDate.parse(dateTime[0]);
        LocalTime time = LocalTime.parse(dateTime[1]);
        return LocalDateTime.of(date, time);
    }
}
