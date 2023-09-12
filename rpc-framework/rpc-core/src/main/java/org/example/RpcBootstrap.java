package org.example;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;
import org.example.utils.Zookeeper.NetUtils;
import org.example.utils.Zookeeper.ZookeeperNode;
import org.example.utils.Zookeeper.ZookeeperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example
 *
 * @author: YW
 * @description:
 * @data: 2023/9/8 19:07
 */
public class RpcBootstrap {
    //日志

    //单例模式，我们希望每个应用程序都只有一个
    private static RpcBootstrap rpcBootstrap=new RpcBootstrap();

    private String applicationName;
    private RegistryConfig registryConfig ;
    private  ProtocolConfig protocolConfig;
    private int port=8088;
    private ZooKeeper zooKeeper;
    private RpcBootstrap(){

        //启动时从初始化需要做什么吗
    }
    public static RpcBootstrap getInstance() {
        return rpcBootstrap;
    }

    //定义名字
    public RpcBootstrap application(String applicationName){
        this.applicationName=applicationName;
        return this;
    }

//    注册中心
    public RpcBootstrap registry(RegistryConfig registryConfig){

        //建立实例
        //这样写耦合会增加
        zooKeeper= ZookeeperUtils.createZookeeper();

        this.registryConfig=registryConfig;
        return this;
    }

    //协议
    public RpcBootstrap protocol(ProtocolConfig protocolConfig){
        this.registryConfig=registryConfig;

        return this;
    }
//    发布服务
    //可能批量发布
    public RpcBootstrap publish(ServiceConfig<?> service){
        //服务名称的节点（持久节点）
        String parentNode=Constant.BASE_PROVIDERS_PATH+"/"+ service.getInterface().getName();
        if(!ZookeeperUtils.exists(zooKeeper,parentNode,null)){
            ZookeeperNode zookeeperNode=new ZookeeperNode(parentNode,null);
            //此处报错
            ZookeeperUtils.createNode(zooKeeper,zookeeperNode,null, CreateMode.PERSISTENT);
        }
        //创建本机的临时节点
        //服务提供方的端口一般自己设定，还需要一个获取ip的方法
        //需要一个局域网ip，不能是localhost,也不是ipv6
        String node=parentNode+"/"+ NetUtils.findLocalIP()+":"+port;
        if(!ZookeeperUtils.exists(zooKeeper,node,null)){
            ZookeeperNode zookeeperNode=new ZookeeperNode(node,null);
            ZookeeperUtils.createNode(zooKeeper,zookeeperNode,null, CreateMode.EPHEMERAL);
        }

        return this;
    }
    public RpcBootstrap publish(List<ServiceConfig> services){
        return this;
    }
    public void start(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //-------------------------调用方api---------------------------------------------------------------
    public RpcBootstrap reference(ReferenceConfig<?> reference){
        return this;
    }
}
