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
package com.sigpwned.pinecone.client;

import static java.util.Objects.requireNonNull;
import java.util.List;
import com.sigpwned.pinecone.client.model.Collection;
import com.sigpwned.pinecone.client.model.CollectionDefinition;
import com.sigpwned.pinecone.client.model.DeleteRequest;
import com.sigpwned.pinecone.client.model.DescribeIndexStatsRequest;
import com.sigpwned.pinecone.client.model.DescribeIndexStatsResponse;
import com.sigpwned.pinecone.client.model.Environment;
import com.sigpwned.pinecone.client.model.FetchRequest;
import com.sigpwned.pinecone.client.model.FetchResponse;
import com.sigpwned.pinecone.client.model.Index;
import com.sigpwned.pinecone.client.model.IndexConfiguration;
import com.sigpwned.pinecone.client.model.IndexDefinition;
import com.sigpwned.pinecone.client.model.QueryRequest;
import com.sigpwned.pinecone.client.model.QueryResponse;
import com.sigpwned.pinecone.client.model.UpdateRequest;
import com.sigpwned.pinecone.client.model.UpsertRequest;
import com.sigpwned.pinecone.client.model.UpsertResponse;
import com.sigpwned.pinecone.client.service.IndexOperationsApi;
import com.sigpwned.pinecone.client.service.VectorOperationsApi;
import com.sigpwned.pinecone.client.util.HttpClients;
import com.sigpwned.pinecone.client.util.Jackson;
import com.sigpwned.pinecone.client.util.Pinecone;

public class PineconeClient {
  private final Environment environment;
  private final String apiKey;
  private final IndexOperationsApi indexApi;

  public PineconeClient(Environment environment, String apiKey) {
    this.environment = requireNonNull(environment);
    this.apiKey = requireNonNull(apiKey);
    this.indexApi = new IndexOperationsApi(newApiClient(indexBaseUrl(environment)));
  }

  /**
   * @param indexName
   * @param indexConfiguration
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.IndexOperationsApi#configureIndex(java.lang.String,
   *      com.sigpwned.pinecone.client.model.IndexConfiguration)
   */
  public void configureIndex(String indexName, IndexConfiguration indexConfiguration)
      throws ApiException {
    getIndexApi().configureIndex(indexName, indexConfiguration);
  }

  /**
   * @param collectionDefinition
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.IndexOperationsApi#createCollection(com.sigpwned.pinecone.client.model.CollectionDefinition)
   */
  public void createCollection(CollectionDefinition collectionDefinition) throws ApiException {
    getIndexApi().createCollection(collectionDefinition);
  }

  /**
   * @param indexDefinition
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.IndexOperationsApi#createIndex(com.sigpwned.pinecone.client.model.IndexDefinition)
   */
  public void createIndex(IndexDefinition indexDefinition) throws ApiException {
    getIndexApi().createIndex(indexDefinition);
  }

  /**
   * @param collectionName
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.IndexOperationsApi#deleteCollection(java.lang.String)
   */
  public void deleteCollection(String collectionName) throws ApiException {
    getIndexApi().deleteCollection(collectionName);
  }

  /**
   * @param indexName
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.IndexOperationsApi#deleteIndex(java.lang.String)
   */
  public void deleteIndex(String indexName) throws ApiException {
    getIndexApi().deleteIndex(indexName);
  }

  /**
   * @param collectionName
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.IndexOperationsApi#describeCollection(java.lang.String)
   */
  public Collection describeCollection(String collectionName) throws ApiException {
    return getIndexApi().describeCollection(collectionName);
  }

  /**
   * @param indexName
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.IndexOperationsApi#describeIndex(java.lang.String)
   */
  public Index describeIndex(String indexName) throws ApiException {
    return getIndexApi().describeIndex(indexName);
  }

  /**
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.IndexOperationsApi#listCollections()
   */
  public List<String> listCollections() throws ApiException {
    return getIndexApi().listCollections();
  }

  /**
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.IndexOperationsApi#listIndexes()
   */
  public List<String> listIndexes() throws ApiException {
    return getIndexApi().listIndexes();
  }


  /**
   * @param deleteRequest
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.VectorOperationsApi#delete(com.sigpwned.pinecone.client.model.DeleteRequest)
   */
  public Object delete(String indexName, String projectId, DeleteRequest deleteRequest)
      throws ApiException {
    return getVectorApi(indexName, projectId).delete(deleteRequest);
  }

  /**
   * @param describeIndexStatsRequest
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.VectorOperationsApi#describeIndexStats(com.sigpwned.pinecone.client.model.DescribeIndexStatsRequest)
   */
  public DescribeIndexStatsResponse describeIndexStats(String indexName, String projectId,
      DescribeIndexStatsRequest describeIndexStatsRequest) throws ApiException {
    return getVectorApi(indexName, projectId).describeIndexStats(describeIndexStatsRequest);
  }

  /**
   * @param fetchRequest
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.VectorOperationsApi#fetch(com.sigpwned.pinecone.client.model.FetchRequest)
   */
  public FetchResponse fetch(String indexName, String projectId, FetchRequest fetchRequest)
      throws ApiException {
    return getVectorApi(indexName, projectId).fetch(fetchRequest);
  }

  /**
   * @param queryRequest
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.VectorOperationsApi#query(com.sigpwned.pinecone.client.model.QueryRequest)
   */
  public QueryResponse query(String indexName, String projectId, QueryRequest queryRequest)
      throws ApiException {
    return getVectorApi(indexName, projectId).query(queryRequest);
  }

  /**
   * @param updateRequest
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.VectorOperationsApi#update(com.sigpwned.pinecone.client.model.UpdateRequest)
   */
  public Object update(String indexName, String projectId, UpdateRequest updateRequest)
      throws ApiException {
    return getVectorApi(indexName, projectId).update(updateRequest);
  }

  /**
   * @param upsertRequest
   * @return
   * @throws ApiException
   * @see com.sigpwned.pinecone.client.service.VectorOperationsApi#upsert(com.sigpwned.pinecone.client.model.UpsertRequest)
   */
  public UpsertResponse upsert(String indexName, String projectId, UpsertRequest upsertRequest)
      throws ApiException {
    return getVectorApi(indexName, projectId).upsert(upsertRequest);
  }

  /**
   * @return the environment
   */
  public Environment getEnvironment() {
    return environment;
  }

  private String getApiKey() {
    return apiKey;
  }

  /** test hook */
  String indexBaseUrl(Environment environment) {
    return Pinecone.defaultIndexBaseUrl(environment);
  }

  private IndexOperationsApi getIndexApi() {
    return indexApi;
  }

  /** test hook */
  String vectorBaseUrl(String indexName, String projectId, Environment environment) {
    return Pinecone.defaultVectorBaseUrl(indexName, projectId, environment);
  }

  private VectorOperationsApi getVectorApi(String indexName, String projectId) {
    return new VectorOperationsApi(
        newApiClient(vectorBaseUrl(indexName, projectId, getEnvironment())));
  }

  private ApiClient newApiClient(String baseUrl) {
    ApiClient result = new ApiClient(HttpClients.defaultHttpClient(), Jackson.MAPPER, baseUrl);
    result.setRequestInterceptor(b -> {
      b.setHeader("Api-Key", getApiKey());
    });
    return result;
  }
}
