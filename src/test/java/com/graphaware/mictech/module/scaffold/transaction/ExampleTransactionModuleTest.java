package com.graphaware.mictech.module.scaffold.transaction;

import org.junit.Test;
import org.neo4j.graphdb.*;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.test.TestGraphDatabaseFactory;

import static com.graphaware.runtime.RuntimeRegistry.getRuntime;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExampleTransactionModuleTest {

    @Test
    public void testModule() throws InterruptedException {

        GraphDatabaseService database = new TestGraphDatabaseFactory()
                .newImpermanentDatabaseBuilder()
                .loadPropertiesFromFile(this.getClass().getClassLoader().getResource("neo4j.properties").getPath())
                .newGraphDatabase();

        getRuntime(database).waitUntilStarted();

        try (Transaction tx = database.beginTx()) {
            Node node = database.createNode();
            node.addLabel(DynamicLabel.label("SomeLabel"));
            node.setProperty("name", "value");
            tx.success();
        }

        try (Transaction tx = database.beginTx()) {
            final ResourceIterator<Node> nodes = database.findNodes(DynamicLabel.label("SomeLabel"));

            for (Node node : IteratorUtil.asCollection(nodes)) {
                assertTrue(node.hasProperty("randomString"));
                assertTrue(node.hasProperty("randomNumber"));

                assertEquals("qwerty", node.getProperty("randomString"));
                assertEquals(42, node.getProperty("randomNumber"));
            }

            tx.success();
        }

        database.shutdown();
    }
}
