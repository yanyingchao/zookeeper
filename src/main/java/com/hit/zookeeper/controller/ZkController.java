package com.hit.zookeeper.controller;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/5/8.
 */
@RestController
public class ZkController {

    @Autowired
    private CuratorFramework zkClient;


    @RequestMapping(value = "/createHello",method = RequestMethod.GET)
    public Object createHello(){
        try {
            zkClient.create().withMode(CreateMode.PERSISTENT).forPath("/hello", "helloWorld".getBytes());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "hello world!";
    }

    @RequestMapping(value="/listener",method = RequestMethod.GET)
    public Object listener() {
        try {
            PathChildrenCache childrenCache = new PathChildrenCache(zkClient, "/hello", false);
            childrenCache.getListenable().addListener((client,event)->{
                switch (event.getType()) {
                    case CHILD_ADDED:
                    case CHILD_UPDATED:
                        System.out.println("修改"+event.getType()+"\t"+event.getData()+"\t"+event.getInitialData());
                        break;
                    case CHILD_REMOVED:
                        huiDiao();
                        break;
                    default:
                        break;
                }
            });
            childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "监视成功";
    }

    public static void huiDiao(){
        System.out.println("触发");
    }
}
