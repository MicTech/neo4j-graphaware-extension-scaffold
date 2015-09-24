package com.graphaware.mictech.module.scaffold.transaction;

import com.graphaware.common.policy.InclusionPolicies;
import com.graphaware.runtime.config.BaseTxDrivenModuleConfiguration;
import com.graphaware.runtime.policy.InclusionPoliciesFactory;

public class ExampleTransactionModuleConfiguration extends BaseTxDrivenModuleConfiguration<ExampleTransactionModuleConfiguration> {

    private static final String DEFAULT_RANDOM_STRING = "asdf";
    private static final int DEFAULT_RANDOM_NUMBER = 123;

    private String randomString;
    private int randomNumber;

    public ExampleTransactionModuleConfiguration(InclusionPolicies inclusionPolicies, String randomString, int randomNumber) {
        super(inclusionPolicies);
        this.randomString = randomString;
        this.randomNumber = randomNumber;
    }

    public static ExampleTransactionModuleConfiguration defaultConfiguration() {
        return new ExampleTransactionModuleConfiguration(InclusionPoliciesFactory.allBusiness(), DEFAULT_RANDOM_STRING, DEFAULT_RANDOM_NUMBER);
    }

    @Override
    protected ExampleTransactionModuleConfiguration newInstance(InclusionPolicies inclusionPolicies) {
        return new ExampleTransactionModuleConfiguration(inclusionPolicies, getRandomString(), getRandomNumber());
    }

    public String getRandomString() {
        return randomString;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public ExampleTransactionModuleConfiguration withRandomString(String randomString) {
        return new ExampleTransactionModuleConfiguration(getInclusionPolicies(), randomString, getRandomNumber());
    }

    public ExampleTransactionModuleConfiguration withRandomNumber(int randomNumber) {
        return new ExampleTransactionModuleConfiguration(getInclusionPolicies(), getRandomString(), randomNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExampleTransactionModuleConfiguration)) return false;
        if (!super.equals(o)) return false;

        ExampleTransactionModuleConfiguration that = (ExampleTransactionModuleConfiguration) o;

        if (randomNumber != that.randomNumber) return false;
        if (!randomString.equals(that.randomString)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + randomString.hashCode();
        result = 31 * result + randomNumber;
        return result;
    }
}
