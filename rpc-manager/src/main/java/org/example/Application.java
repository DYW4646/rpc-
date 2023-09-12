package org.example;

import org.apache.zookeeper.*;
import org.example.utils.Zookeeper.ZookeeperNode;
import org.example.utils.Zookeeper.ZookeeperUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example
 *
 * @author: YW
 * @description:zookeeper注册中心的管理页面
 * @data: 2023/9/9 14:40
 */
public class Application {
//    private  static final Logger log= LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {

        //创建zookeeper
        ZooKeeper zooKeeper= ZookeeperUtils.createZookeeper();

//            定义节点和数据
        String basePath="/rpcmetadata";
        String providerPath=basePath+"/providers";
        String consumerPath=basePath+"/consumers";
        ZookeeperNode baseNode=new ZookeeperNode(basePath,null);
        ZookeeperNode providerNode=new ZookeeperNode(providerPath,null);
        ZookeeperNode consumerNode=new ZookeeperNode(consumerPath,null);
        //创建节点
        List.of(baseNode,providerNode,consumerNode).forEach(node ->{
            ZookeeperUtils.createNode(zooKeeper,node,null,CreateMode.PERSISTENT);
        });
        ZookeeperUtils.close(zooKeeper);

    }




}
