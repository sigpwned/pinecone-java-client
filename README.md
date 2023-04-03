# openapi-java-client [![tests](https://github.com/sigpwned/pinecone-java-client/actions/workflows/tests.yml/badge.svg)](https://github.com/sigpwned/pinecone-java-client/actions/workflows/tests.yml) ![Maven Central](https://img.shields.io/maven-central/v/com.sigpwned/pinecone-java-client?style=plastic)

This is a Java client for the [Pinecone vector database API](https://www.pinecone.io/). While there is an official [Pinecone Java client](https://github.com/pinecone-io/pinecone-java-client), at the time of this writing, it does not support all endpoints. Specifically:

    The Java client doesn't support managing Pinecone services, only reading and writing from existing indices. To create or delete an index, use the Python client.

This client supports all endpoints. Note that most of the client's code is generated from [this unofficial OpenAPI spec of the Pinecone API](https://github.com/sigpwned/pinecone-openapi-spec).