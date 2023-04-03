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

import static java.lang.String.format;
import com.sigpwned.pinecone.client.model.Environment;

public final class Pinecone {
  private Pinecone() {}

  public static String defaultIndexBaseUrl(Environment environment) {
    return format("https://controller.%s.pinecone.io", environment);
  }

  public static String defaultVectorBaseUrl(String indexName, String projectId,
      Environment environment) {
    return format("https://%s-%s.svc.%s.pinecone.io", indexName, projectId, environment);
  }
}
