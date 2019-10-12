# Spring Boot, GraphQL & Couchbase Demo
A demonstration project using Spring Boot and Couchbase to show how GraphQL can be used.  

### Sample Document
Create a new document in Couchbase with the below structure. The Document ID should be the one you wish to use in the GraphQL queries.
 
```json
{
    "id": 1001,
    "title": "A Test Product",
    "stock": 3
}
```

### Sample GraphQL Query
The following is an example query that would retrieve `product=1001` from Couchbase and only return the `title` and `stock` fields.

```graphql
{
  product(id: 1001) {
    title
    stock
  }
}
```
