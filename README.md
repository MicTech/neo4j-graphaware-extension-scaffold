# Scaffold for the Neo4j Unmanaged Extension with GraphAware Framework

**Released under [BeerWare license](https://people.freebsd.org/~phk/).**

Basic scaffold for your own Neo4j Unmanaged Extension based on the GraphAware Framework.

## Required knowledge
* Java
* Maven
* Neo4j (beginner)

## Extending REST API of Neo4j server
* Example how to extend existing REST API of Neo4j with your own REST endpoints.
* package com.graphaware.mictech.module.scaffold.api

## Transactional Module
* Example how to use Transaction Event API with GraphAware Framework.
* It's like stored procedures in traditional RDBMS.
* package com.graphaware.mictech.module.scaffold.transaction
* [Neo4j Improved Transaction Event API](http://graphaware.com/neo4j/transactions/2014/07/11/neo4j-transaction-event-api.html)

## Never forget (GraphAware Framework)
* Your own extension must fulfill following patterns for the package name:
- com.\*\*.graphaware.\*\*
- org.\*\*.graphaware.\*\*
- net.\*\*.graphaware.\*\*

## How to build it and deploy it
* mvn clean install
* copy target/example-extension-1.0-SNAPSHOT-jar-with-dependencies.jar to neo4j/plugins
* download [GraphAware Framework](http://graphaware.com/products/) to neo4j/plugins
* restart neo4j