package com.hit.zookeeper.bean;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryUntilElapsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/5/8.
 */
@Configuration
public class zkClientConfigure {
    @Autowired
    private ZookeeperProperties zookeeperProperties;

    @Bean
    public CuratorFramework zkClient() {
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zookeeperProperties.getZkAddress())
                .retryPolicy(new RetryUntilElapsed(Integer.MAX_VALUE, 60000))
                .sessionTimeoutMs(100000)
                .connectionTimeoutMs(100000).build();
        client.start();
        return client;
    }
}
