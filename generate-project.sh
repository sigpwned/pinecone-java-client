MAJOR_VERSION=20230401.1
PATCH_VERSION=0

openapi-generator generate \
    --api-package com.sigpwned.pinecone.client.service \
    --model-package com.sigpwned.pinecone.client.model \
    --group-id com.sigpwned \
    --artifact-id pinecone-client \
    --artifact-version "${MAJOR_VERSION}.${PATCH_VERSION}" \
    --generator-name java \
    --input-spec "https://raw.githubusercontent.com/sigpwned/pinecone-openapi-spec/v${MAJOR_VERSION}/openapi.yml" \
    --additional-properties 'dateLibrary=java8,library=native,serializationLibrary=jackson,returnResponse=false,useRuntimeException=true,sourceFolder=target/generated-sources/openapi,testOutput=target/generated-test-sources/openapi,artifactUrl=https://github.com/sigpwned/pinecone-java-client,artifactDescription=Java client for Pinecone Vector Search Database,scmConnection=scm:git:ssh://git@github.com/sigpwned/pinecone-java-client.git,scmDeveloperConnection=scm:git:ssh://git@github.com/sigpwned/pinecone-java-client.git,scmUrl=https://github.com/sigpwned/pinecone-java-client,licenseName=Apache License Version 2.0,licenseUrl=http://www.apache.org/licenses/LICENSE-2.0.txt,developerName=Andy Boothe,developerEmail=andy@sigpwned.com,developerOrganization=sigpwned,developerOrganizationUrl=https://www.sigpwned.com/' \
    --output .
