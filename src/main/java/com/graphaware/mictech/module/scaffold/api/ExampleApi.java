package com.graphaware.mictech.module.scaffold.api;

import com.graphaware.api.JsonNode;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//Will be accessible on the URL localhost:7474/graphaware/api/...
@Controller
@RequestMapping("/api")
public class ExampleApi {

    private final GraphDatabaseService database;

    @Autowired
    public ExampleApi(GraphDatabaseService database) {
        this.database = database;
    }

    @RequestMapping(value = "/users/{nickname}", method = RequestMethod.GET)
    @ResponseBody
    public JsonNode getUserByNickname(@PathVariable String nickname) {

        JsonNode result;

        try (Transaction tx = database.beginTx()) {
            result = new JsonNode(database.findNode(DynamicLabel.label("User"), "nickname", nickname));
            tx.success();
        }

        return result;
    }
}
