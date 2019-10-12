package io.mattmajor.demo.graphql.facade;

import com.couchbase.client.java.document.json.JsonObject;
import io.mattmajor.demo.graphql.service.GraphQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/query")
public class QueryResource {
    private final GraphQLService graphQLService;

    @Autowired
    public QueryResource(final GraphQLService graphQLService) {
        this.graphQLService = graphQLService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Object graphQl(@RequestBody final String query) {
        final JsonObject requestBody = JsonObject.fromJson(query);
        return graphQLService.executeQuery(requestBody.get("query").toString());
    }
}
