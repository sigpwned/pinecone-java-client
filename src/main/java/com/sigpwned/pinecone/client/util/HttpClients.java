package com.sigpwned.pinecone.client.util;

import java.net.http.HttpClient;

public final class HttpClients {
  private HttpClients() {}

  public static HttpClient.Builder defaultHttpClient() {
    return HttpClient.newBuilder();
  }
}
