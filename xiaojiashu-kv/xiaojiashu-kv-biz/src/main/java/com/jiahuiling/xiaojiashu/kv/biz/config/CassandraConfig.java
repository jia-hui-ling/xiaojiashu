package com.jiahuiling.xiaojiashu.kv.biz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.stereotype.Component;

/**
 * @Author 贾慧玲
 * @Date 2026/9/10 11:09
 * @Description TODO
 */

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {
    @Value("${spring.cassandra.keyspace-name}")
    private String keySpace;
    @Value("${spring.cassandra.contact-points}")
    private String contactPoints;
    @Value("${spring.cassandra.port}")
    private int port;

    /*
     * Provide a keyspace name to the configuration.
     */
    @Override
    public String getKeyspaceName() {
        return keySpace;
    }

    @Override
    public String getContactPoints() {
        return contactPoints;
    }

    @Override
    public int getPort() {
        return port;
    }

}
