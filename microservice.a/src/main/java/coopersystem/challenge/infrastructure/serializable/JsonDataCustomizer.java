package coopersystem.challenge.infrastructure.serializable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.quarkus.jackson.ObjectMapperCustomizer;

import javax.inject.Singleton;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Singleton
public class JsonDataCustomizer implements ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
        JavaTimeModule module = new JavaTimeModule();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        LocalDateDeserializer localDateDeserializer = new LocalDateDeserializer(dateTimeFormatter);
        module.addDeserializer(LocalDate.class, localDateDeserializer);

        LocalDateSerializer localDateSerializer= new LocalDateSerializer(dateTimeFormatter);
        module.addSerializer(LocalDate.class, localDateSerializer);

        objectMapper.registerModule(module);
    }
}
