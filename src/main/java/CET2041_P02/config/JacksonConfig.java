package CET2041_P02.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.MediaType;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonConfig implements ContextResolver<ObjectMapper> {

  private final ObjectMapper mapper;

  public JacksonConfig() {
    mapper = new ObjectMapper();

    // Register module to support java.time types
    mapper.registerModule(new JavaTimeModule());

    // Important: disable timestamps for dates — this makes LocalDate -> "yyyy-MM-dd"
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    // Optional improvements:
    // mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    // mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);

    // If you want a custom pattern for LocalDate: register serializers/deserializers
    // but for ISO-8601 default behavior above is usually fine.
  }

  @Override
  public ObjectMapper getContext(Class<?> type) {
    return mapper;
  }
}

