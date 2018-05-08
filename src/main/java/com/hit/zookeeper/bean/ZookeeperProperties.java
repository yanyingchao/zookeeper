package com.hit.zookeeper.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/5/8.
 */
@Data
@Component
@ConfigurationProperties(prefix = "zk")
public class ZookeeperProperties {
    private String zkAddress;
}
