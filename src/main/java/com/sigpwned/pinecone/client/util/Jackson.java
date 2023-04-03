package com.sigpwned.pinecone.client.util;

import org.openapitools.jackson.nullable.JsonNullableModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class Jackson {
  private Jackson() {}

  public static final ObjectMapper MAPPER = defaultObjectMapper();

  public static ObjectMapper defaultObjectMapper() {
    ObjectMapper result = new ObjectMapper();
    result.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    result.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
    result.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    result.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    result.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    result.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    result.registerModule(new JavaTimeModule());
    result.registerModule(new JsonNullableModule());
    return result;
  }
}
