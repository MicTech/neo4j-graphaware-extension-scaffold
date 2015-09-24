package com.graphaware.mictech.module.scaffold.api;

import com.graphaware.test.data.CypherPopulator;
import com.graphaware.test.data.DatabasePopulator;
import com.graphaware.test.integration.GraphAwareApiTest;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

public class ExampleApiTest extends GraphAwareApiTest {

    @Override
    protected DatabasePopulator databasePopulator() {
        return new CypherPopulator() {
            @Override
            protected String[] statementGroups() {
                return new String[]{
                        "MERGE (:User {nickname: 'Neo', type: 'Hero'})",
                        "MERGE (:User {nickname: 'Trinity', type: 'Hero'})",
                        "MERGE (:User {nickname: 'Morpheus', type: 'Hero'})",
                        "MERGE (:User {nickname: 'Smith', type: 'Villain'})",
                        "MERGE (:User {nickname: 'Cypher', type: 'Villain'})"
                };
            }
        };
    }

    @Test
    public void shouldReturnUserByNickname() throws IOException {
        String response = httpClient.get(baseUrl() + "/api/users/Neo", HttpStatus.OK_200);

        assertFalse(response.isEmpty());

        assertEquals("{\"id\":0,\"properties\":{\"nickname\":\"Neo\",\"type\":\"Hero\"},\"labels\":[\"User\"]}", response);
    }
}
