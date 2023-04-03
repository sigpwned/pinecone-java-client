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
