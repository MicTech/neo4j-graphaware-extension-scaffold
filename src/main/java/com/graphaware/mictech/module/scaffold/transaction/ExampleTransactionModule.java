package com.graphaware.mictech.module.scaffold.transaction;

import com.graphaware.runtime.module.BaseTxDrivenModule;
import com.graphaware.runtime.module.DeliberateTransactionRollbackException;
import com.graphaware.tx.event.improved.api.Change;
import com.graphaware.tx.event.improved.api.ImprovedTransactionData;
import org.neo4j.graphdb.Node;

public class ExampleTransactionModule extends BaseTxDrivenModule<Void> {

    private final ExampleTransactionModuleConfiguration moduleConfiguration;

    protected ExampleTransactionModule(String moduleId, ExampleTransactionModuleConfiguration configuration) {
        super(moduleId);
        this.moduleConfiguration = configuration;
    }

    @Override
    public Void beforeCommit(ImprovedTransactionData transactionData) throws DeliberateTransactionRollbackException {

        for (Node node : transactionData.getAllCreatedNodes()) {
            node.setProperty("randomString", moduleConfiguration.getRandomString());
            node.setProperty("randomNumber", moduleConfiguration.getRandomNumber());
        }

        for (Node node : transactionData.getAllDeletedNodes()) {

        }

        for (Change<Node> change : transactionData.getAllChangedNodes()) {

        }

        return null;
    }
}
