package io.mattmajor.demo.graphql.configuration;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class CouchbaseConfiguration {
    @Value("${couchbase.host}")
    private String couchbaseHost;

    @Value("${couchbase.username}")
    private String couchbaseUsername;

    @Value("${couchbase.password}")
    private String couchbasePassword;

    @Value("${couchbase.bucket-name}")
    private String couchbaseBucketName;

    @Bean
    public Cluster couchbaseCluster() {
        final Cluster cluster = CouchbaseCluster.create(couchbaseHost);
        cluster.authenticate(couchbaseUsername, couchbasePassword);
        return cluster;
    }

    @Bean
    @DependsOn("couchbaseCluster")
    public Bucket couchbaseBucket() {
        return couchbaseCluster().openBucket(couchbaseBucketName);
    }
}
