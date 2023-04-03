/*-
 * =================================LICENSE_START==================================
 * pinecone-java-client
 * ====================================SECTION=====================================
 * Copyright (C) 2023 Andy Boothe
 * ====================================SECTION=====================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ==================================LICENSE_END===================================
 */
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
