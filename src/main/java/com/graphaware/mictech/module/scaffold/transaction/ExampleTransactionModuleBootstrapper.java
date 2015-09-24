package com.graphaware.mictech.module.scaffold.transaction;

import com.graphaware.runtime.module.RuntimeModule;
import com.graphaware.runtime.module.RuntimeModuleBootstrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ExampleTransactionModuleBootstrapper implements RuntimeModuleBootstrapper {

    private static final Logger LOG = LoggerFactory.getLogger(ExampleTransactionModuleBootstrapper.class);

    private static final String MODULE_STRING = "randomString";
    private static final String MODULE_NUMBER = "randomNumber";

    @Override
    public RuntimeModule bootstrapModule(String moduleId, Map<String, String> config, org.neo4j.graphdb.GraphDatabaseService database) {
        ExampleTransactionModuleConfiguration configuration = ExampleTransactionModuleConfiguration.defaultConfiguration();

        if (config.get(MODULE_STRING) != null && config.get(MODULE_STRING).length() > 0) {
            configuration = configuration.withRandomString(config.get(MODULE_STRING));
            LOG.info("randomString set to {}", configuration.getRandomString());
        }

        if (config.get(MODULE_NUMBER) != null && config.get(MODULE_NUMBER).length() > 0) {
            configuration = configuration.withRandomNumber(Integer.parseInt(config.get(MODULE_NUMBER)));
            LOG.info("randomNumber set to {}", configuration.getRandomNumber());
        }

        return new ExampleTransactionModule(moduleId, configuration);
    }
}
