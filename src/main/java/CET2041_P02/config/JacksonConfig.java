package CET2041_P02.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.MediaType;

/**
 * JAX-RS provider that configures the application's Jackson {@link ObjectMapper}.
 * This ensures consistent JSON serialization and deserialization behavior across
 * all endpoints.
 *
 * <p>The configuration includes support for Java 8+ date and time types via
 * {@link JavaTimeModule}, and disables writing dates as numeric timestamps so
 * that {@code LocalDate} and other date-time values are serialized in ISO-8601
 * string format.</p>
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonConfig implements ContextResolver<ObjectMapper> {

  /**
   * The customized ObjectMapper instance used by JAX-RS for all JSON processing.
   */
  private final ObjectMapper mapper;

  /**
   * Initializes the ObjectMapper with modules and settings required for
   * proper JSON handling of Java time classes and ISO date formatting.
   */
  public JacksonConfig() {
    mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  /**
   * Returns the configured ObjectMapper to the JAX-RS runtime.
   *
   * @param type the class being serialized or deserialized (ignored here)
   * @return the shared ObjectMapper instance
   */
  @Override
  public ObjectMapper getContext(Class<?> type) {
    return mapper;
  }
}

